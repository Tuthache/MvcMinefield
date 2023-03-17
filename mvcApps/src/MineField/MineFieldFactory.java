package MineField;
import mvc.*;

public class MineFieldFactory extends AppFactory{
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
        switch(name){
            case "N": {
                return new moveCommand(model);
            }
        }
    }
}
