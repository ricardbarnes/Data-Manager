/*
 * PersistenceManager distributor
 */
package core.persistence;

/**
 *
 * @author Ricard P. Barnes
 */
public class PersistenceManager {

    // Select only one type
    private static final boolean DB4O_PERSISTENCE = true; // 0
    private static final boolean JDBC_PERSISTENCE = false; // 1

    private Persistence manager;

    public PersistenceManager() {
        if (PersistenceManager.DB4O_PERSISTENCE) {
            manager = new DB4O();
        }
        if (PersistenceManager.JDBC_PERSISTENCE) {
            manager = new JDBC();
        }
    }

    public Persistence getManager() {
        return manager;
    }

}
