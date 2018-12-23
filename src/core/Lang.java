/*
 * en-US
 * Human readable language related items for the program core
 */
package core;

import javax.swing.JOptionPane;

/**
 *
 * @author Ricard P. Barnes
 */
public class Lang {

    // Window title
    public static final String TITLE = "Enterprise Manager";

    public static class Exception {

        /**
         * Exception causes {Title, Message, Message type (JOptionPane...)}
         */
        public static final Object[][] MESSAGES = {
            {"ERROR", "Unknown error", JOptionPane.ERROR_MESSAGE}, // 0
            {"ERROR", "Persistence error", JOptionPane.ERROR_MESSAGE}, // 1
            {"Not found", "Object not found", JOptionPane.INFORMATION_MESSAGE}, // 2
            {"Duplicate", "Object already exists", JOptionPane.INFORMATION_MESSAGE}, // 3
            {"Nothing found", "Database does not contain any object of that class", JOptionPane.INFORMATION_MESSAGE}, // 4
            {"Empty fields", "Fields cannot be empty", JOptionPane.INFORMATION_MESSAGE} // 5
        };

    }  

}
