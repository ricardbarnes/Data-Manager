/*
 * Main CommonController class
 */
package main;

import core.commons.CommonController;
import core.persistence.Persistence;
import core.persistence.PersistenceManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import views.Menu;

/**
 *
 * @author Ricard P. Barnes
 */
public class Controller extends CommonController {
    
    private final Menu mainMenu;
    private modules.products.Controller productsController;
    public Persistence persistence;

    public Controller() {
        persistence = new PersistenceManager().getManager();
        mainMenu = new views.Menu(Lang.Menu.Main.BUTTONS);
        super.addButtonListeners(mainMenu.getButtons());
    }

    public Persistence getPersistence() {
        return persistence;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton[] buttons = mainMenu.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                selectedOption(i);
            }
        }
    }

    public void selectedOption(int option) {

        switch (option) {
            case Index.Main.EXIT:
                System.exit(0);
                break;
            case Index.Main.BILLING_MANAGEMENT:
                JOptionPane.showMessageDialog(mainMenu.getFrame(), Lang.Miscelaneous.UNAVAIABLE);
                break;
            case Index.Main.CLIENT_MANAGEMENT:
                JOptionPane.showMessageDialog(mainMenu.getFrame(), Lang.Miscelaneous.UNAVAIABLE);
                break;
            case Index.Main.PRODUCT_MANAGEMENT:
                mainMenu.getFrame().setVisible(false);
                productsController = new modules.products.Controller();
                break;
        }

    }

    public Menu getMainMenu() {
        return mainMenu;
    }

    public modules.products.Controller getProductsController() {
        return productsController;
    }

}
