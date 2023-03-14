package mvc;

abstract public class Command {
    public Model model;
    public void execute(){
    }
    public Command(Model m){
        this.model = m;
    }
}
