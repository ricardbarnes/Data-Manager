/*
 * Controllers mother class
 */
package core.commons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Ricard P. Barnes
 */
public abstract class CommonController implements ActionListener {
    
    protected static JButton[] currentButtons;
    
    /**
     * Add this object listener to the passed buttons
     * @param buttons 
     */
    protected void addButtonListeners(JButton[] buttons) {
        currentButtons = buttons;
        for (JButton button : currentButtons) {
            button.addActionListener(this);
        }
    }
    
    @Override
    public abstract void actionPerformed(ActionEvent e);

}
