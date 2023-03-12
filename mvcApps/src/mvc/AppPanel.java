package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {
    private AppFactory af;
    private Model model = new Model();
    private View view = new View(model);
    public AppPanel.ControlPanel controlPanel = new AppPanel.ControlPanel();
    public AppPanel(AppFactory af){
        this.af = af;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public class ControlPanel extends JPanel{
        public ControlPanel(){

        }
    }

    public void display(){

    }
}
