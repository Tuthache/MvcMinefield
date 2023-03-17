package MineField;

import mvc.*;

public class moveCommand extends Command{
    public moveCommand(Model m){
        super(m);
    }

    public void execute(){
        MineField mineField = (MineField) model;
        mineField.move();
    }
    public void move(){
        Heading heading = mineField.getHeading();
        switch(heading){
            case "N":{
                return
            }
            case "NE":{

            }
            case "NW":{

            }
            case "E":{

            }
            case "W":{

            }
            case "S":{

            }
            case "SE":{

            }
            case "SW":{

            }
            default: {
                
            }
        }
    }

}
