package MineField;
import mvc.*;

import javax.swing.*;
import java.awt.*;

public class MineFieldView extends View{
    private Cell cells[][];
    public MineFieldView(MineField m) {

        super(m);
        int dim = m.getDim();
        cells = new Cell[dim][dim];
        setLayout(new GridLayout(dim,dim));
        for(int row = 0; row < dim; row++) {
            for(int col = 0; col < dim; col++) {
                cells[row][col] = new Cell();
                cells[row][col].setText("?");
                cells[row][col].patch = m.getPatch(row,col);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(cells[row][col].patch.occupied) {
                    cells[row][col].setBackground(Color.RED);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    cells[row][col].setText("" + cells[row][col].patch);
                }
                if(cells[row][col].patch.goal) {
                    cells[row][col].setBackground(Color.WHITE);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                this.add(cells[row][col]);
            }
        }
        setPreferredSize(new Dimension(dim * 40, dim * 40));
    }
    private void initView() {

    }
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        MineField field = (MineField)model;
        Color oldColor = gc.getColor();


    }
}