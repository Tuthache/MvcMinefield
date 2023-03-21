package MineField;

import mvc.*;
import java.util.*;

/*Parameters:
Minefield is a square with length and width of 20
There will be 80 mines on the minefield or 20% of 400 cells

For testing purposes use NumberOfMines = 5 and gridSize = 5
 */
public class MineField extends Model{
    final private int NumberOfMines = 10;    //Total amount of mines on the minefield
    final private int gridSize = 5;    //length and width of grid
    private static Cell[][] grid;  //The grid in which the player is on
    private Cell player, goal;    //The cell that the player is on, The cell that is the goal to reach
    private Heading heading;    //The heading of the player
    private Random rand = new Random(); //Creates random object
    private Set<Cell> traversed;    // Set that contains all traversed cells

    //Creates the Minefield Object
    public MineField() {
        Cell[][] grid = new Cell[gridSize][gridSize];
        heading = Heading.E;
        traversed = new HashSet<Cell>();
        player = grid[0][0];
        goal = grid[gridSize-1][gridSize-1];
        fillGrid(grid);
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
        }
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
        MineField minefield = new MineField();
    }
}