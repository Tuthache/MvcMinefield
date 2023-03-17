package MineField;

import mvc.*;

public class MoveCommand extends Command{
    private int x;
    private int y;
    public MoveCommand(Model m){
        super(m);
    }

    public void execute(){
        MineField mineField = (MineField) model;
        mineField.move(x, y);
    }
    public void setMove(int x, int y){
        this.x = x;
        this.y = y;
    }

}
