package MineField;
import mvc.*;
import javax.swing.*;
import java.awt.*;

public class MineFieldView extends View{
    public class Cell extends JLabel{
        Patch patch;
    }
    private Cell cells[][];

    public MineFieldView (MineField m){
        super((Model)m);

        int dim = m.getDim();
        cells = new Cell[dim][dim];
        for (int row = 0; row< dim; row++){
            for (int col = 0; col< dim; col++){
                cells[row][col] = new Cell();
                cells[row][col].setText("?");
                cells[row][col].patch = m.getPatcb(row, col);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (cells[row][col].patch.occupied){
                    cells[row][col].setBackground(Color.RED);
                    cells[row][col]
                }
            }
        }
    }

}
