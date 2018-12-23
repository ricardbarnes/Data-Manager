/*
 * Controller for material management
 */
package modules.products.material;

import views.TableModel;
import core.commons.CommonController;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import views.Menu;
import views.Form;
import views.List;
import core.ProgramException;
import core.persistence.Persistence;

/**
 *
 * @author Ricard P. Barnes
 */
public class Controller extends CommonController {

    private static final int ADD = 1;
    private static final int REMOVE = 2;
    private static final int EDIT = 3;

    private static Menu menu;
    private static Form form;
    private static List list;
    private static Material material;
    private static int option;
    private static int formType;

    public Controller() {
        menu = new Menu(Lang.Menu.BUTTONS);
        super.addButtonListeners(menu.getButtons());
    }

    public static Form getForm() {
        return form;
    }

    public static void setForm(Form form) {
        Controller.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton[] buttons;

        if ((form == null) & list == null) {
            buttons = menu.getButtons();
            for (option = 0; option < buttons.length; option++) {
                if (e.getSource() == buttons[option]) {
                    try {
                        selectedMenuOption(option);
                    } catch (InstantiationException | IllegalAccessException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

        if (form != null) {
            switch (formType) {
                case ADD:
                    buttons = form.getButtons();
                    for (option = 0; option < buttons.length; option++) {
                        if (e.getSource() == buttons[option]) {
                            try {
                                selectedAddOption(option);
                            } catch (ProgramException ex) {
                                JOptionPane.showMessageDialog(menu.getFrame(), ex.getMessage(), ex.getTitle(), ex.getType());
                            }
                        }
                    }
                    break;
                case REMOVE:
                    buttons = form.getButtons();
                    for (option = 0; option < buttons.length; option++) {
                        if (e.getSource() == buttons[option]) {
                            selectedRemoveOption(option);
                        }
                    }
                    break;
                case EDIT:
                    buttons = form.getButtons();
                    for (option = 0; option < buttons.length; option++) {
                        if (e.getSource() == buttons[option]) {
                            selectedEditOption(option);
                        }
                    }
                    break;
            }

        }

        if (list != null) {
            buttons = list.getButtons();
            for (option = 0; option < buttons.length; option++) {
                if (e.getSource() == buttons[option]) {
                    selectedListOption(option);
                }
            }
        }

    }

    private void selectedMenuOption(int option) throws InstantiationException, IllegalAccessException {

        switch (option) {
            case Index.Menu.BACK:
                modules.products.Controller.getProductsMenu().getFrame().setVisible(true);
                menu.getFrame().setVisible(false);
                break;
            case Index.Menu.ADD:
                formType = ADD;
                form = new Form(Index.Form.Add.PARAMETERS, Lang.Form.Add.LABELS, Lang.Form.Add.BUTTONS);
                form.getLabels()[0].setEnabled(false);
                form.getTextFields()[0].setEditable(false);
                form.getTextFields()[0].setEnabled(false);
                form.getTextFields()[0].setText(Lang.Form.CODE_TEXT);
                super.addButtonListeners(form.getButtons());
                break;
            case Index.Menu.REMOVE:
                formType = REMOVE;
                form = new Form(Index.Form.Remove.PARAMETERS, Lang.Form.Remove.LABELS, Lang.Form.Remove.BUTTONS);
                super.addButtonListeners(form.getButtons());
                break;
            case Index.Menu.EDIT:
                formType = EDIT;
                form = new Form(Index.Form.Edit.PARAMETERS, Lang.Form.Edit.LABELS, Lang.Form.Edit.BUTTONS);
                Object[][] config = {
                    {Index.Form.Edit.TextFields.CODE, true},
                    {Index.Form.Edit.TextFields.DESC, false},
                    {Index.Form.Edit.TextFields.PRICE, false},
                    {Index.Form.Edit.TextFields.UNITY, false},
                    {Index.Form.Edit.TextFields.VALUE_A, false},
                    {Index.Form.Edit.TextFields.VALUE_B, false}
                };
                form.configEditable(config);
                super.addButtonListeners(form.getButtons());
                break;
            case Index.Menu.LIST:
                try {
                    list = new List(new TableModel(new Material("example"), Lang.List.COLUMNS), Lang.List.BUTTONS);
                    super.addButtonListeners(list.getButtons());
                } catch (ProgramException ex) {
                    JOptionPane.showMessageDialog(menu.getFrame(), ex.getMessage(), ex.getTitle(), ex.getType());
                }
                break;
        }
    }

    private void selectedAddOption(int option) throws ProgramException {

        formType = ADD;

        switch (option) {
            case Index.Form.Add.Buttons.EXIT:
                form.getFrame().setVisible(false);
                form = null;
                formType = 0;
                break;
            case Index.Form.Add.Buttons.SAVE:
                try {
                    material = new Material(form.serialize());
                    Persistence.save(material);
                    material = null;
                    form.getFrame().setVisible(false);
                    form = null;
                    formType = 0;
                } catch (ProgramException ex) {
                    JOptionPane.showMessageDialog(form.getFrame(), ex.getMessage(), ex.getTitle(), ex.getType());
                }
                break;
        }

    }

    private void selectedRemoveOption(int option) {

        formType = REMOVE;

        switch (option) {
            case Index.Form.Remove.Buttons.DELETE:
                try {
                    int code = Integer.valueOf(form.getTextFields()[0].getText());
                    material = new Material(code);
                    Persistence.delete(material);
                    material = null;
                    JOptionPane.showMessageDialog(
                            form.getFrame(),
                            Lang.Form.Remove.DELETED[Lang.Form.Remove.MESSAGE],
                            (String) Lang.Form.Remove.DELETED[Lang.Form.Remove.TITLE],
                            (Integer) Lang.Form.Remove.DELETED[Lang.Form.Remove.TYPE]
                    );
                } catch (ProgramException ex) {
                    JOptionPane.showMessageDialog(form.getFrame(), ex.getMessage(), ex.getTitle(), ex.getType());
                }
                break;
            case Index.Form.Remove.Buttons.EXIT:
                form.getFrame().setVisible(false);
                form = null;
                formType = 0;
                break;
        }

    }

    private void selectedEditOption(int option) {

        formType = EDIT;

        switch (option) {
            case Index.Form.Edit.Buttons.SEARCH:
                try {
                    String code = form.getTextFields()[0].getText();
                    material = new Material(Integer.parseInt(code));
                    material = (Material) Persistence.load(material);
                    for (int i = 1; i < form.getTextFields().length; i++) {
                        form.getTextFields()[i].setText(material.getAttribute(i));
                    }
                    form.toggleEditable();
                    form.getButtons()[0].setEnabled(false);
                } catch (ProgramException ex) {
                    material = null;
                    JOptionPane.showMessageDialog(form.getFrame(), ex.getMessage(), ex.getTitle(), ex.getType());
                } catch (NumberFormatException unused) {
                    // Just skipping
                }
                break;
            case Index.Form.Edit.Buttons.SAVE:
                if (material != null) { // A material has been successfully loaded
                    try {
                        material.setAttributes(form.serialize());
                        Persistence.save(material);
                        form.getFrame().setVisible(false);
                        form = null;
                    } catch (ProgramException ex) {
                        JOptionPane.showMessageDialog(form.getFrame(), ex.getMessage(), ex.getTitle(), ex.getType());
                    } catch (NumberFormatException unused) {
                        JOptionPane.showMessageDialog(
                                form.getFrame(),
                                main.Lang.Miscelaneous.BAD_INPUT,
                                main.Lang.Miscelaneous.INPUT_ERROR,
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
                break;
            case Index.Form.Edit.Buttons.EXIT:
                form.getFrame().setVisible(false);
                form = null;
                formType = 0;
                break;
        }

    }

    private void selectedListOption(int option) {

        switch (option) {
            default:
                list.getFrame().setVisible(false);
                list = null;
                break;
        }

    }

}
