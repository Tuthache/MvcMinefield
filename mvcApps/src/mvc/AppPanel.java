package mvc;

import javax.swing.*;
import java.awt.*;
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
        this.setLayout(new GridLayout());

        this.af = af;
        Model m = af.makeModel();
        view = af.makeView(m);
        for (int i = 0; i < af.getEditCommands().length; i++){
            Command command = af.makeEditCommand(m, af.getEditCommands()[i], this);
        }
        controlPanel = new AppPanel.ControlPanel();
        add(controlPanel);
        controlPanel.setPreferredSize(new Dimension(300, 500));
        add(view);
        view.setPreferredSize(new Dimension(500, 500));

    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Save as", "Open", "Quit"}, this);
        JMenu editMenu = Utilities.makeMenu("Edit", af.getEditCommands(), this);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"Help", "About"}, this);
        result.add(fileMenu);
        result.add(editMenu);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch(cmmd){
                //file menu options
                case "Save": {
                    Utilities.save(view.model, view.model.getUnsavedChanges());
                    break;
                }
                case "Save as": {
                    Utilities.save(view.model, true);
                    break;
                }
                case "Open": {
                    view.setModel(Utilities.open(view.model));
                    break;
                }
                case "New":{
                    if (this.view.model.getUnsavedChanges()){
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            Model m = af.makeModel();
                            view.setModel(m);
                        }
                    } else {
                        Model m  = af.makeModel();
                        view.setModel(m);
                    }
                    break;
                }
                case "Quit": {
                    if (this.view.model.getUnsavedChanges()){
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                            System.exit(0);
                    } else {
                        System.exit(0);
                    }
                    break;
                }
                case "Help": {
                    Utilities.inform(af.getHelp());
                    break;
                }
                case "About":{
                    Utilities.inform(af.about());
                    break;
                }
                default: {
                    Command chosenCommand = af.makeEditCommand(view.model, cmmd, this);
                    if (chosenCommand != null){
                        chosenCommand.execute();
                    }
                    else {
                        throw new Exception("Unrecognized command: " + cmmd);
                    }

                }
            }
        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }
    public class ControlPanel extends JPanel{
        private JPanel controlButtons;
        public ControlPanel(){
            this.setMinimumSize(new Dimension(300, 500));
            setBackground(Color.PINK);
            controlButtons = new JPanel();
        }
    }

    public void display(){
        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(af.getTitle());
        frame.setSize(1000, 500);
        frame.setVisible(true);
    }
}
