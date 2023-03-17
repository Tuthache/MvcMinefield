package MineField;
import mvc.*;

public class MineFieldFactory implements AppFactory{
    public Model makeModel(){
        return new MineField();
    }
    public View makeView(Model m){
        return new MineFieldView((MineField) m);
    }
    public String getTitle(){
        return "MineField Application";
    }
    public String[] getHelp(){
        return new String[] {"N: Moves player North",
                "NE: Moves player North East",
                "NW: Moves player North West",
                "E: Moves player East",
                "W: Moves player West",
                "S: Moves player South",
                "SE: Moves player South East",
                "SW: Moves player South West"};
    }
    public String about(){
        return "MineField Application produced by Allen Tran, Austin Nguyen, Yingchen Bai";
    }
    public String[] getEditCommands(){
        return new String[] {"N", "NE", "NW","E", "W", "S", "SE", "SW"};
    }
    public Command makeEditCommand(Model model, String name, Object object){
        Command action = null;
        switch(name){
            case "N": {
                action = new MoveCommand((MineField)model);
                ((MoveCommand) action).setMove(0,-1);
                return action;
            }
            case "NE": {
                action = new MoveCommand((MineField)model);
                ((MoveCommand) action).setMove(1,-1);
                return action;
            }
            case "NW": {
                action = new MoveCommand((MineField)model);
                ((MoveCommand) action).setMove(-1,-1);
                return action;
            }
            case "W": {
                action = new MoveCommand((MineField)model);
                ((MoveCommand) action).setMove(-1,0);
                return action;
            }
            case "E": {
                action = new MoveCommand((MineField)model);
                ((MoveCommand) action).setMove(1,0);
                return action;
            }
            case "S": {
                action = new MoveCommand((MineField)model);
                ((MoveCommand) action).setMove(0,1);
                return action;
            }
            case "SE": {
                action = new MoveCommand((MineField)model);
                ((MoveCommand) action).setMove(1,1);
                return action;
            }
            case "SW": {
                action = new MoveCommand((MineField)model);
                ((MoveCommand) action).setMove(-1,1);
                return action;
            }
        }
        return null;
    }
}
