package MineField;

public class Cell {
    private boolean hasMine;    //if true has a bomb, if not no bomb on cell
    private boolean hasTraversed;   //if true set cell to white, if not black
    public Cell(boolean hasMine, boolean hasTraversed){
        this.hasMine = hasMine;
        this.hasTraversed = hasTraversed;
    }

    public boolean getHasMine() {
        return hasMine;
    }
    public boolean getHasTraversed() {
        return hasTraversed;
    }
    public void setHasMine(boolean hasMine){
        this.hasMine = hasMine;
    }
    public void setHasTraversed(boolean hasTraversed){
        this.hasTraversed = hasTraversed;
    }

}
