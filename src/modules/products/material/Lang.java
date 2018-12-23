/*
 * en-US
 * Names for buttons
 */
package modules.products.material;

import javax.swing.JOptionPane;

/**
 *
 * @author Ricard P. Barnes
 */
public class Lang {

    public static class Menu {

        public static final String[] BUTTONS = {
            "Add",
            "Remove",
            "Edit",
            "List",
            "Back"
        };

    }

    public static class Form {

        public static final String CODE_TEXT = "Automatic";

        public static class Add {

            public static final String[] BUTTONS = {
                "Save",
                "Exit"
            };

            public static final String[] LABELS = {
                "Code",
                "Description",
                "Price",
                "Unity type",
                "\"A\" value",
                "\"B\" value"
            };

        }

        public static class Remove {

            public static final int TITLE = 0;
            public static final int MESSAGE = 1;
            public static final int TYPE = 2;

            public static final Object[] DELETED = {
                "Deleted material",
                "This material was successfully deleted",
                JOptionPane.INFORMATION_MESSAGE
            };

            public static final String[] BUTTONS = {
                "Delete",
                "Exit"
            };

            public static final String[] LABELS = {
                "Code (only the number)",};

        }

        public static class Edit {

            public static final Object[] NOT_FOUND = {
                "Not found", "This material does not exist", JOptionPane.INFORMATION_MESSAGE
            };

            public static final String[] BUTTONS = {
                "Search",
                "Save",
                "Back"
            };

            public static final String[] LABELS = {
                "Code (search via number)",
                "Description",
                "Price",
                "Unity type",
                "\"A\" value",
                "\"B\" value"
            };

        }

    }

    public static class List {

        public static final String[] COLUMNS = {
            "Code", // 0
            "Description", // 1
            "Price", // 2
            "Unity", // 3
            "Value_A", // 4
            "Value_B" // 5
        };

        public static final String[] BUTTONS = {
            "Exit"
        };

        public static final String EMPTY = "There are no materials yet.";

    }

}
