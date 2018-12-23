/*
 * Exception handler class
 */
package core;

/**
 *
 * @author Ricard P. Barnes
 */
public class ProgramException extends Exception {

    public static final int PERSISTENCE_ERROR = 1;
    public static final int NOT_FOUND = 2;
    public static final int ALREADY_EXISTS = 3;
    public static final int NOTHING_FOUND = 4;
    public static final int EMPTY_FIELDS = 5;

    private final int index;
    private final String title;
    private final String message;
    private final int type;

    public ProgramException() {
        index = 0;
        title = (String) Lang.Exception.MESSAGES[index][ColumnIndex.TITLE];
        message = (String) Lang.Exception.MESSAGES[index][ColumnIndex.MESSAGE];
        type = (Integer) Lang.Exception.MESSAGES[index][ColumnIndex.TYPE];
    }

    public ProgramException(int pCode) {
        index = pCode;
        title = (String) Lang.Exception.MESSAGES[index][ColumnIndex.TITLE];
        message = (String) Lang.Exception.MESSAGES[index][ColumnIndex.MESSAGE];
        type = (Integer) Lang.Exception.MESSAGES[index][ColumnIndex.TYPE];
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }
    
    private static class ColumnIndex {
        
        private static final int TITLE = 0;
        private static final int MESSAGE = 1;
        private static final int TYPE = 2;
        
    }
    
}
