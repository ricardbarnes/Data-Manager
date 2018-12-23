/*
 * Material list builder
 */
package views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import main.Lang;
import core.ProgramException;

/**
 *
 * @author Ricard P. Barnes
 */
public class List {

    private JFrame frame;
    private JTable table;
    private JButton[] buttons;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[] buttons) {
        this.buttons = buttons;
    }

    public List(TableModel model, String[] buttonNames) throws ProgramException, InstantiationException, IllegalAccessException {

        frame = new JFrame();
        frame.setTitle(Lang.TITLE);
        frame.setLayout(new GridLayout(0, 1));
        table = new JTable(model);
        
        this.buttons = new JButton[buttonNames.length];
        for (int i = 0; i < buttonNames.length; i++) {
            this.buttons[i] = new JButton(buttonNames[i]);
        }
        
        frame.add(new JScrollPane(table));
        for (JButton button : this.buttons) {
            frame.add(button);
        }
        frame.setSize(Config.WIDTH, Config.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
