package MineField;

import javax.swing.*;

public class Cell extends JPanel{
    private boolean hasMine;    //if true has a bomb, if not no bomb on cell
    private boolean hasTraversed;   //if true set cell to white, if not black
    private int minesNearby;
    private JLabel label;

    public Cell(boolean hasMine, boolean hasTraversed, int minesNearby){
        this.hasMine = hasMine;
        this.hasTraversed = hasTraversed;
        this.minesNearby = minesNearby;
        label = new JLabel();
        add(label);
    }

    public boolean getHasMine() {
        return hasMine;
    }
    public boolean getHasTraversed() {
        return hasTraversed;
    }
    public int getMinesNearby(){
        return minesNearby;
    }
    public void setHasMine(boolean hasMine){
        this.hasMine = hasMine;
    }
    public void setHasTraversed(boolean hasTraversed){
        this.hasTraversed = hasTraversed;
    }
    //Converts cell into a readable object where matrix is mined or not
    public void setMinesNearby(int minesNearby){
        this.minesNearby = minesNearby;
    }
    public void setText(String text){
        label.setText(text);
    }
    public String toString(){
        if (this.getHasMine()){
            return "m";
        } else {
            return Integer.toString(this.getMinesNearby());
        }
    }
}
