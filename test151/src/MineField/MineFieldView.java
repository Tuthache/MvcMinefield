package MineField;
import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class MineFieldView extends View{
    private Cell cells[][];
    public MineFieldView(MineField m) {
        super(m);
        propertyChange(null);
    }
    public void propertyChange(PropertyChangeEvent change) {
        removeAll();
        MineField field = (MineField) this.model;
        int size = field.getGridSize();
        System.out.print("Gridsize: " + size);
        cells = field.getGrid();
        setLayout(new GridLayout(size,size));
        for(int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = new Cell(cells[row][col].getHasMine(), cells[row][col].getHasTraversed(), cells[row][col].getMinesNearby());
                cells[row][col].setText("?");
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                field.showCell(field.getPlayerX(), field.getPlayerY());
                if (cells[row][col].getHasTraversed()){
                    field.showCell(row, col);
                    cells[row][col].setBackground(Color.WHITE);
                    cells[row][col].setText(""+ cells[row][col].toString());
                    if (cells[row][col].getHasMine()){
                        cells[row][col].setBackground(Color.RED);
                        cells[row][col].setText("M");
                    }
                }
                if (cells[row][col] == cells[size - 1][size - 1]) {
                    cells[row][col].setBackground(Color.WHITE);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                if (cells[row][col] == cells[field.getPlayerX()][field.getPlayerY()]){
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    cells[row][col].setText(""+ cells[row][col].toString());
                    if (cells[row][col] == cells[size-1][size-1]){
                        cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    }
                }
                add(cells[row][col]);
            }
        }
        revalidate();
    }
}