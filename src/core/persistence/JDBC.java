/*
 * JDBC Manager
 */
package core.persistence;

import core.ObjectCounter;
import core.ProgramException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricard P. Barnes
 */
public class JDBC extends Persistence{

    private static Connection connection;

    private static String selectQuery;
    private static String deleteQuery;
    private static String insertQuery;
    private static String updateQuery;

    private static PreparedStatement selectSt;
    private static PreparedStatement deleteSt;
    private static PreparedStatement insertSt;
    private static PreparedStatement updateSt;

    private static String tableName;

    public static void connect() throws ProgramException {

        String url = "jdbc:derby://localhost:1527/data_manager";
        String user = "root";
        String pass = "root";

        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException unused) {
            connection = null;
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        }

    }

    public static void disconnect() throws ProgramException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException unused) {
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        } finally {
            connection = null;
            selectQuery = null;
            deleteQuery = null;
            insertQuery = null;
            updateQuery = null;
            selectSt = null;
            deleteSt = null;
            insertSt = null;
            updateSt = null;
            tableName = null;
        }
    }

    @Override
    public void save(Persistent aThis) throws ProgramException {
        ResultSet results;
        tableName = aThis.getClass().getSimpleName().toUpperCase();
        try {
            selectQuery = "SELECT * FROM " + tableName + " WHERE ID = ?";
            connect();
            selectSt = connection.prepareStatement(selectQuery);
            selectSt.setString(1, aThis.getId());
            results = selectSt.executeQuery();
            if (results.next()) {
                delete(aThis);
            }
            insertQuery = "INSERT INTO " + tableName + getInsertString(aThis);
            insertSt = connection.prepareStatement(insertQuery);
            for (int i = 0; i < aThis.getAttributeNumber(); i++) {
                insertSt.setObject(i + 1, aThis.getAttribute(i));
            }
            insertSt.executeUpdate();
            disconnect();
        } catch (SQLException unused) {
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        }
    }

    @Override
    public Persistent load(Persistent aThis) throws ProgramException {
        ResultSet results;
        Relational retrievedObject = (Relational) aThis;
        tableName = aThis.getClass().getSimpleName().toUpperCase();
        try {
            selectQuery = "SELECT * FROM " + tableName + " WHERE ID = ?";
            connect();
            selectSt = connection.prepareStatement(selectQuery);
            selectSt.setString(1, aThis.getId());
            results = selectSt.executeQuery();
            if (results.next()) {
                for (int j = 0; j < ((Persistent) aThis).getAttributeNumber(); j++) {
                    retrievedObject.setAttributes(j, results.getObject(((Relational) aThis).getColumnName(j)));
                }
            } else {
                throw new ProgramException(ProgramException.NOTHING_FOUND);
            }
        } catch (SQLException unused) {
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        } finally {
            disconnect();
        }
        return (Persistent) retrievedObject;
    }

    @Override
    public void delete(Persistent aThis) throws ProgramException {
        ResultSet results;
        boolean internal = false;

        if (connection == null) {
            try {
                tableName = aThis.getClass().getSimpleName().toUpperCase();
                connect();
            } catch (ProgramException unused) {
                throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
            }
        } else {
            internal = true;
        }

        try {
            selectQuery = "SELECT * FROM " + tableName + " WHERE ID = ?";
            selectSt = connection.prepareStatement(selectQuery);
            selectSt.setString(1, aThis.getId());
            results = selectSt.executeQuery();
            if (results.next()) {
                deleteQuery = "DELETE FROM " + tableName + " WHERE ID = ?";
                deleteSt = connection.prepareStatement(deleteQuery);
                deleteSt.setString(1, aThis.getId());
                deleteSt.executeUpdate();
            } else {
                throw new ProgramException(ProgramException.NOT_FOUND);
            }
        } catch (SQLException unused) {
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        }

        if (!internal) {
            disconnect();
        }
    }

    @Override
    public List<Persistent> getAll(Persistent example) throws ProgramException {
        ResultSet results;
        Constructor<?> constructor;
        Class<?> exampleClass = example.getClass();
        Relational retrievedObject = null;
        List<Persistent> objectList = new ArrayList<>();
        tableName = example.getClass().getSimpleName().toUpperCase();
        try {
            selectQuery = "SELECT * FROM " + tableName;
            connect();
            selectSt = connection.prepareStatement(selectQuery);
            results = selectSt.executeQuery();
            if (results.next()) {
                objectList = new ArrayList<>();
                for (int i = 0; i < rowCount(); i++) {
                    try {
                        constructor = exampleClass.getConstructor(String.class);
                        Object newObject = constructor.newInstance(new Object[]{"model"});
                        retrievedObject = (Relational) newObject;
                    } catch (NoSuchMethodException
                            | SecurityException
                            | InstantiationException
                            | IllegalAccessException
                            | IllegalArgumentException
                            | InvocationTargetException unused) {
                        throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
                    }
                    for (int j = 0; j < ((Persistent) example).getAttributeNumber(); j++) {
                        retrievedObject.setAttributes(j, results.getObject(((Relational) example).getColumnName(j)));
                    }
                    objectList.add((Persistent) retrievedObject);
                    results.next();
                }
            } else {
                throw new ProgramException(ProgramException.NOTHING_FOUND);
            }
        } catch (SQLException unused) {
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        } finally {
            disconnect();
        }
        return objectList;
    }

    @Override
    public void saveCounter(ObjectCounter counter) throws ProgramException {
        ResultSet results;

        try {
            selectQuery = "SELECT * FROM OBJECTCOUNTER WHERE TYPE = ?";
            connect();
            selectSt = connection.prepareStatement(selectQuery);
            selectSt.setString(1, counter.getType().getName());
            results = selectSt.executeQuery();
            if (results.next()) {
                updateQuery = "UPDATE OBJECTCOUNTER SET NUMBER = ? WHERE TYPE = ?";
                updateSt = connection.prepareStatement(updateQuery);
                updateSt.setInt(1, counter.getObjectNumber());
                updateSt.setString(2, counter.getType().getName());
                updateSt.executeUpdate();
            } else {
                insertQuery = "INSERT INTO OBJECTCOUNTER(TYPE, NUMBER) VALUES (?, ?)";
                insertSt = connection.prepareStatement(insertQuery);
                insertSt.setString(1, counter.getType().getName());
                insertSt.setInt(2, counter.getObjectNumber());
                insertSt.executeUpdate();
            }
            disconnect();
        } catch (SQLException unused) {
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        }
    }

    @Override
    public ObjectCounter loadCounter(ObjectCounter counter) throws ProgramException {

        ResultSet results;
        ObjectCounter loadedCounter = null;

        try {
            selectQuery = "SELECT * FROM OBJECTCOUNTER WHERE TYPE = ?";
            connect();
            selectSt = connection.prepareStatement(selectQuery);

            selectSt.setString(1, counter.getType().getName());
            results = selectSt.executeQuery();
            if (results.next()) {
                try {
                    loadedCounter = new ObjectCounter(Class.forName(results.getString("TYPE")), results.getInt("NUMBER"));
                } catch (ClassNotFoundException unused) {
                    throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
                }
            } else {
                saveCounter(counter);
                loadedCounter = counter;
            }
        } catch (SQLException unused) {
            disconnect();
            throw new ProgramException(ProgramException.PERSISTENCE_ERROR);
        }
        disconnect();
        return loadedCounter;
    }

    private static int rowCount() throws ProgramException {
        int rows = 0;
        String query = "SELECT COUNT(1) FROM " + tableName;
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            if (results.next()) {
                rows = results.getInt(1);
            }
        } catch (SQLException unused) {
            return rows;
        }
        return rows;
    }

    private static String getInsertString(Persistent aThis) {
        String str = "(";
        for (int i = 0; i < aThis.getAttributeNumber(); i++) {
            str += ((Relational) aThis).getColumnName(i);
            if (i < aThis.getAttributeNumber() - 1) {
                str += ",";
            }
        }
        str += ") VALUES (";
        for (int i = 0; i < aThis.getAttributeNumber(); i++) {
            str += "?";
            if (i < aThis.getAttributeNumber() - 1) {
                str += ",";
            }
        }
        str += ")";
        return str;
    }

}
