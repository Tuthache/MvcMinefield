package MineField;
import mvc.*;

import javax.swing.*;
import java.awt.*;

public class MineFieldView extends View{
    private Cell cells[][];
    public MineFieldView(MineField m) {

        super(m);
        int size = m.getGridSize();
        cells = m.getGrid();
        setLayout(new GridLayout(size,size));
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++) {
                cells[row][col] = new Cell(cells[row][col].getHasMine(), cells[row][col].getHasTraversed(), cells[row][col].getMinesNearby());
                cells[row][col].setText("?");
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(cells[row][col].getHasMine()) {
                    cells[row][col].setBackground(Color.RED);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    cells[row][col].setText("" + cells[row][col].toString());
                }
                if(cells[row][col] == m.goal) {
                    cells[row][col].setBackground(Color.WHITE);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                this.add(cells[row][col]);
            }
        }
        setPreferredSize(new Dimension(size * 40, size * 40));
    }
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        MineField field = (MineField)model;
        Color oldColor = gc.getColor();


    }
}