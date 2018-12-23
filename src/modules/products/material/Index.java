/*
 * View structure for the material view classes
 */
package modules.products.material;

/**
 *
 * @author Ricard P. Barnes
 */
public class Index {

    public static class Menu {

        public static final int ADD = 0;
        public static final int REMOVE = 1;
        public static final int EDIT = 2;
        public static final int LIST = 3;
        public static final int BACK = 4;

    }

    public static class Form {

        // For parameters array
        public static final int BUTTONS_IDX = 0;
        public static final int LABELS_IDX = 1;
        public static final int TEXTFIELDS_IDX = 2;

        public static class Add {

            public static final int BUTTONS = 2;
            public static final int LABELS = 6;
            public static final int TEXTFIELDS = 6;

            public static final int[] PARAMETERS = {BUTTONS, LABELS, TEXTFIELDS};

            public static class Buttons {

                public static final int SAVE = 0;
                public static final int EXIT = 1;

            }

            public static class TextFields {

                public static final int CODE = 0;
                public static final int DESC = 1;
                public static final int PRICE = 2;
                public static final int UNITY = 3;
                public static final int VALUE_A = 4;
                public static final int VALUE_B = 5;

            }

        }

        public static class Remove {

            public static final int BUTTONS = 2;
            public static final int LABELS = 1;
            public static final int TEXTFIELDS = 1;

            public static final int[] PARAMETERS = {BUTTONS, LABELS, TEXTFIELDS};

            public static class Buttons {

                public static final int DELETE = 0;
                public static final int EXIT = 1;

            }

            public static class TextFields {

                public static final int CODE = 0;
                public static final int DESC = 1;
                public static final int PRICE = 2;
                public static final int UNITY = 3;
                public static final int VALUE_A = 4;
                public static final int VALUE_B = 5;

            }

        }

        public static class Edit {

            public static final int BUTTONS = 3;
            public static final int LABELS = 6;
            public static final int TEXTFIELDS = 6;

            public static final int[] PARAMETERS = {BUTTONS, LABELS, TEXTFIELDS};

            public static class Buttons {

                public static final int SEARCH = 0;
                public static final int SAVE = 1;
                public static final int EXIT = 2;

            }

            public static class TextFields {

                public static final int CODE = 0;
                public static final int DESC = 1;
                public static final int PRICE = 2;
                public static final int UNITY = 3;
                public static final int VALUE_A = 4;
                public static final int VALUE_B = 5;

            }

        }

    }

}
