package mvc;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements PropertyChangeListener {

    protected Model model;
    public View(Model m){
        this.model = m;
        model.addPropertyChangeListener(this);
    }
    public void setModel(Model model){
        model.removePropertyChangeListener(this);
        this.model = model;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        repaint();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
