/*
 * Product controller class
 */
package modules.products;

import core.commons.CommonController;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import views.Menu;
import main.Main;

/**
 *
 * @author Ricard P. Barnes
 */
public class Controller extends CommonController {

    private final Menu productsMenu;
    private modules.products.materials.Controller materialController;

    public Controller() {
        productsMenu = new Menu(Lang.Menu.Main.BUTTONS);
        super.addButtonListeners(productsMenu.getButtons());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton[] buttons = productsMenu.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                selectedOption(i);
            }
        }
    }

    public void selectedOption(int option) {

        switch (option) {
            case Index.Main.BACK:
                Main.getController().getMainMenu().getFrame().setVisible(true);
                productsMenu.getFrame().setVisible(false);
                break;
            case Index.Main.MATERIAL_MANAGEMENT:
                materialController = new modules.products.materials.Controller();
                productsMenu.getFrame().setVisible(false);
                break;
            case Index.Main.SERVICE_MANAGEMENT:
                JOptionPane.showMessageDialog(productsMenu.getFrame(), main.Lang.Miscelaneous.UNAVAIABLE);
//                productsMenu.getFrame().setVisible(false);
                break;
        }
    }

    public Menu getProductsMenu() {
        return productsMenu;
    }

//    public modules.products.materials.Controller getMaterialController() {
//        return materialController;
//    }

}
