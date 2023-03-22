package MineField;

import mvc.*;

public class MoveCommand extends Command{
    private Heading heading;
    public MoveCommand(Model m, Heading heading){
        super(m);
        this.heading = heading;
    }

    public void execute(){
        MineField mineField = (MineField) model;
        mineField.move(heading);
    }

}
