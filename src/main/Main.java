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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller controlador = new Controller();
        });
    }

}
