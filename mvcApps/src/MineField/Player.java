package MineField;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private List<Cell> path;    //Unsure if needed or not, has cells in which has been traversed
    private Heading heading;    //Heading of the player
    private Cell location;      //Location of the player based on the cell
    public Player() {
        heading = Heading.EAST;
        path = new LinkedList<Cell>();
    }
    public void move(){

    }
}
