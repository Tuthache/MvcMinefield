package MineField;

public class Cell {
    private boolean hasMine;    //if true has a bomb, if not no bomb on cell
    private boolean hasTraversed;   //if true set cell to white, if not black
    private int minesNearby;
    public Cell(boolean hasMine, boolean hasTraversed, int minesNearby){
        this.hasMine = hasMine;
        this.hasTraversed = hasTraversed;
        this.minesNearby = minesNearby;
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
    public String toString(){
        return "" + getHasMine();
    }
}
