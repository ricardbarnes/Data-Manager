/*
 * Form builder
 */
package views;

import core.ProgramException;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import main.Lang;
import modules.products.material.Index;

/**
 *
 * @author Ricard P. Barnes
 */
public class Form {

    private JFrame frame;
    private JLabel[] labels;
    private JTextField[] textFields;
    private JButton[] buttons;
    private boolean[] configEditable;

    /**
     * Form constructor
     *
     * @param parameters
     * @param labelNames
     * @param buttonNames
     */
    public Form(int[] parameters, String[] labelNames, String[] buttonNames) {

        labels = new JLabel[parameters[Index.Form.LABELS_IDX]];
        textFields = new JTextField[parameters[Index.Form.TEXTFIELDS_IDX]];
        buttons = new JButton[parameters[Index.Form.BUTTONS_IDX]];

        frame = new JFrame();
        frame.setTitle(Lang.TITLE);
        frame.setLayout(new GridLayout(0, 1));

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(labelNames[i]);
            frame.add(labels[i]);
            textFields[i] = new JTextField();
            frame.add(textFields[i]);
        }

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonNames[i]);
            frame.add(buttons[i]);
        }

        frame.setSize(Config.WIDTH, Config.HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public JTextField[] getTextFields() {
        return textFields;
    }

    public void setTextFields(JTextField[] textFields) {
        this.textFields = textFields;
    }

    public JButton[] getButtons() {
        return buttons;
    }

    public void setButtons(JButton[] buttons) {
        this.buttons = buttons;
    }

    public String[] serialize() throws ProgramException {
        String[] serialized = new String[this.getTextFields().length];
        for (int i = 0; i < this.getTextFields().length; i++) {
            String text = this.getTextFields()[i].getText();
            if (text.isEmpty()) {
                throw new ProgramException(ProgramException.EMPTY_FIELDS);
            }
            serialized[i] = text;
        }
        return serialized;
    }

    /**
     * Configurate your form with the propper enabled/disabled displays
     *
     * @param config A set with integer (index) / boolean (state)
     */
    public void configEditable(Object[][] config) {
        configEditable = new boolean[config.length];
        for (int i = 0; i < config.length; i++) {
            configEditable[i] = (boolean) config[i][1];
            this.getLabels()[i].setEnabled(configEditable[i]);
            this.getTextFields()[i].setEnabled(configEditable[i]);
        }
    }

    public void toggleEditable() {
        for (int i = 0; i < configEditable.length; i++) {
            if (configEditable[i]) {
                configEditable[i] = false;
            } else {
                configEditable[i] = true;
            }
            this.getLabels()[i].setEnabled(configEditable[i]);
            this.getTextFields()[i].setEnabled(configEditable[i]);
        }
    }

}
