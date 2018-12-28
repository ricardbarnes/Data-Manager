/*
 * Main class
 */
package main;

import javax.swing.SwingUtilities;

/**
 *
 * @author Ricard P. Barnes
 */
public class Main {
    
    private static Controller controller;

    public static Controller getController() {
        return controller;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            controller = new Controller();
        });
    }

}
