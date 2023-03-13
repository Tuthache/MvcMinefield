package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {
    private AppFactory af;
    private View view;
    public AppPanel.ControlPanel controlPanel;
    private String fileName;
    private boolean unsavedChanges;
    public AppPanel(AppFactory af){
        this.af = af;
        Model m = af.makeModel();
        view = new View(m);
        controlPanel = new AppPanel.ControlPanel();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch(cmmd){
                case "Save": {
                    if (fileName == null){
                        fileName = Utilities.getFileName((String) null, false);
                    }
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
                    os.writeObject(this.view.model);
                    os.close();
                    break;
                }
                case "Save as": {
                    fileName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
                    os.writeObject(this.view.model);
                    os.close();
                    break;
                }
                case "Open": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        this.view.model = (Model) is.readObject();
                        view.setModel(af.makeModel());
                        is.close();
                    }
                }
                case "New":{
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        Model m  = af.makeModel();
                        view.setModel(m);
                    }
                    break;
                }
                case "Quit": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                        System.exit(0);
                    break;
                }
                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }
        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }
    public class ControlPanel extends JPanel{
        public ControlPanel(){

        }
    }

    public void display(){

    }
}
