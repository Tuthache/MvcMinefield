package MineField;

import java.util.*;

public class MineField {
    private Cell[][] grid;  //The grid in which the player is on
    private Player player;  //The player
    public MineField() {
        Cell[][] grid = new Cell[20][20];
        player = new Player();
    }
}
