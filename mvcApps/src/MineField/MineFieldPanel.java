package MineField;
import mvc.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MineFieldPanel extends AppPanel{
    private JButton n;
    private JButton ne;
    private JButton nw;
    private JButton e;
    private JButton w;
    private JButton s;
    private JButton se;
    private JButton sw;

    public MineFieldPanel(AppFactory factory){
        super(factory);
        MineField object = (MineField)view.model;
        controlPanel.setLayout(new GridLayout(4, 2));
        n = new JButton("N");
        n.addActionListener(this);
        ne = new JButton("NE");
        ne.addActionListener(this);
        nw = new JButton("NW");
        nw.addActionListener(this);
        e = new JButton("E");
        e.addActionListener(this);
        w = new JButton("W");
        w.addActionListener(this);
        s = new JButton("S");
        s.addActionListener(this);
        se = new JButton("SE");
        se.addActionListener(this);
        sw = new JButton("SW");
        sw.addActionListener(this);
    }
    public void actionPerformed(ActionEvent action){
        super.actionPerformed(action);
        MineField object = (MineField) view.model;
        if (action.getSource() == n){
            object.setHeading(Heading.N);
        } else if (action.getSource() == ne) {
            object.setHeading(Heading.NE);
        } else if (action.getSource() == nw) {
            object.setHeading(Heading.NW);
        } else if (action.getSource() == w) {
            object.setHeading(Heading.W);
        } else if (action.getSource() == e) {
            object.setHeading(Heading.E);
        } else if (action.getSource() == s) {
            object.setHeading(Heading.S);
        } else if (action.getSource() == se) {
            object.setHeading(Heading.SE);
        } else if (action.getSource() == sw) {
            object.setHeading(Heading.SW);
        }
    }
    public static void main(String[] args){
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }
}
