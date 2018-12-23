/*
 * Menu builder
 */
package views;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import main.Lang;

/**
 *
 * @author Ricard P. Barnes
 */
public class Menu {

    private JFrame frame;
    private JButton[] buttons;

    public Menu(String[] buttonNames) {

        buttons = new JButton[buttonNames.length];

        frame = new JFrame();
        frame.setTitle(Lang.TITLE);
        frame.setLayout(new GridLayout(0, 1));
        frame.setSize(Config.WIDTH, Config.HEIGHT);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            frame.add(buttons[i]);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[] menuButtons) {
        this.buttons = menuButtons;
    }

}
