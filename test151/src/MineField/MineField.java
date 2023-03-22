package MineField;

import mvc.*;

import javax.swing.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/*Parameters:
Minefield is a square with length and width of 20
There will be 80 mines on the minefield or 20% of 400 cells

For testing purposes use NumberOfMines = 5 and gridSize = 5
 */
public class MineField extends Model{
    private int NumberOfMines;    //Total amount of mines on the minefield
    private int gridSize;    //length and width of grid
    private static Cell[][] grid;  //The grid in which the player is on
    protected Cell player, goal;    //The cell that the player is on, The cell that is the goal to reach
    private int playerX, playerY;
    private Heading heading;    //The heading of the player
    private Random rand = new Random(); //Creates random object
    private Set<Cell> traversed;    // Set that contains all traversed cells
    public Cell[][] getGrid(){
        return grid;
    }
    public int getPlayerX(){
        return playerX;
    }
    public int getPlayerY(){
        return playerY;
    }
    public int getGridSize(){
        return gridSize;
    }
    public Cell getPlayer(){
        return player;
    }
    public void move(Heading heading){
        switch (heading){
            case W: {
                playerY--;
                changed();
                break;
            }
            case SW: {
                playerY--;
                playerX++;
                changed();
                break;
            }
            case NW: {
                playerY--;
                playerX--;
                changed();
                break;
            }
            case N: {
                playerX--;
                changed();
                break;
            }
            case S: {
                playerX++;
                changed();
                break;
            }
            case E: {
                playerY++;
                changed();
                break;
            }
            case SE: {
                playerY++;
                playerX++;
                changed();
                break;
            }
            case NE: {
                playerY++;
                playerX--;
                changed();
                break;
            }
        }
    }
    public void showCell(int x, int y){
        Cell current = grid[x][y];
        if(!current.getHasTraversed()){
            current.setHasTraversed(true);
            if (current == goal){
                JOptionPane.showMessageDialog(null, "You beat the game!");
            } else if (current.getHasMine()){
                JOptionPane.showMessageDialog(null, "You hit a mine!");
            } else {
                current.setText(Integer.toString(current.getMinesNearby()));
            }
        }
    }
    //Creates the Minefield Object
    public MineField(int gridSize, int numberOfMines) {
        this.gridSize = gridSize;
        this.NumberOfMines = numberOfMines;
        this.playerX = 0;
        this.playerY = 0;
        grid = new Cell[gridSize][gridSize];
        heading = Heading.E;
        traversed = new HashSet<Cell>();
        fillGrid(grid);
        player = grid[0][0];
        goal = grid[gridSize-1][gridSize-1];
        generateMines(grid);
        generateNearbyMines(grid);
        //printMatrix(grid);
    }
    //Helper method that fills grid with empty cells
    public void fillGrid(Cell[][] grid){
        for(int row = 0; row < gridSize; row++){
            for(int col = 0; col < gridSize; col++){
                grid[row][col] = new Cell(false, false, 0);
            }
        }
    }
    //Helper method that generates mines on the grid by randomly selecting the coordinates
    public void generateMines(Cell[][] grid){
        int row, col;
        for(int i = 0; i < NumberOfMines; i++){
            row = rand.nextInt(gridSize);
            col = rand.nextInt(gridSize);
            Cell mine = grid[row][col];
            if(mine.getHasMine() == false && mine != player && mine != goal){ //Checks if cell already has a mine or if it is goal or player
                mine.setHasMine(true);    //Sets cell to have a bomb
            } else{     //If not retry
                i--;
            }
        }
        //printMatrix(grid);
    }
    //Unoptimized, generates number of mines nearby. For each
    //cell we must check where the cell is, to check nearby cells
    public void generateNearbyMines(Cell[][] grid){
        for(int row = 0; row < gridSize; row++) {
            for(int col = 0; col < gridSize; col++) {
                int nearbyMines = 0;
                for(int i = row-1; i <= row+1; i++) {
                    for(int j = col-1; j <= col+1; j++) {
                        if(i >= 0 && i < gridSize && j >= 0 && j < gridSize) {
                            if(grid[i][j].getHasMine()) {
                                nearbyMines++;
                            }
                        }
                    }
                }
                grid[row][col].setMinesNearby(nearbyMines);
            }
        }
        /*for(int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int count = 0;
                switch(row){
                    case 0:
                        switch(col) {
                            case 0:     //top left corner
                                if (grid[row + 1][col].getHasMine() == true) {
                                    count++;
                                }
                                if (grid[row][col + 1].getHasMine() == true) {
                                    count++;
                                }
                                if (grid[row + 1][col + 1].getHasMine() == true) {
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                            case gridSize - 1:      //top right corner
                                if(grid[row][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col-1].getHasMine() == true){
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                            default:    //top-most row, excluding corners
                                if(grid[row][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row][col+1].getHasMine() == true){
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                        }
                        break;
                    case gridSize-1:
                        switch(col){
                            case 0:     //bottom left corner
                                if(grid[row-1][col].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row][col+1].getHasMine() == true){
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                            case gridSize-1:    //bottom right corner
                                if(grid[row][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col].getHasMine() == true){
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                            default:    //bottom-most row, excluding corners
                                if(grid[row][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row][col+1].getHasMine() == true){
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                        }
                        break;
                    default:
                        switch(col){
                            case 0:     //left-most column
                                if(grid[row-1][col].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col].getHasMine() == true){
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                            case gridSize-1:    //right-most column
                                if(grid[row-1][col].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col].getHasMine() == true){
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                            default:    //All cells that are not corners/sides
                                if(grid[row-1][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row-1][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col+1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row+1][col-1].getHasMine() == true){
                                    count++;
                                }
                                if(grid[row][col-1].getHasMine() == true){
                                    count++;
                                }
                                grid[row][col].setMinesNearby(count);
                                break;
                        }
                        break;
                }
            }
        }*/
    }
    //Helper method that prints out matrix
    public static void printMatrix(Cell[][] grid){
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].getMinesNearby() + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].getHasMine()+ " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        MineField minefield = new MineField(10, 25);
    }
}