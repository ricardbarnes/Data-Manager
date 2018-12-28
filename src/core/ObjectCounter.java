/*
 * Counts every created commonObject
 */
package core;

import core.persistence.Persistent;
import main.Main;
import main.Controller;

/**
 *
 * @author Ricard P. Barnes
 */
public final class ObjectCounter implements Persistent {

    // public static final ID = 0; // Don't use
    public static final int TYPE = 0;
    public static final int OBJECT_NUMBER = 1;

    public static final int ATTRIBUTES = 2;

    private String id = "counter"; // useless
    private Class type;
    private int objectNumber;

    /**
     * Standard constructor.
     * 
     * @param pType The object class
     * @throws ProgramException
     */
    public ObjectCounter(Class pType) throws ProgramException {
        type = pType;
        count();
    }

    /**
     * Instanced as example class
     *
     * @param str Any string, but recommended to be an autodescriptive one, like
     * "example".
     */
    public ObjectCounter(String str) {
    }

    /**
     * Special ObjectCounter constructor for JDBC(SQL) data sources
     * 
     * @param pType The objec class. A cast will be needed.
     * @param number The object number.
     */
    public ObjectCounter(Class pType, int number) {
        type = pType;
        objectNumber = number;
    }
    
    public int getObjectNumber() {
        return objectNumber;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ObjectCounter{" + "objectClass=" + type + ", objectNumber=" + objectNumber + '}';
    }

    @Override
    public void setId(String code) {
        id = code;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getAttributeNumber() {
        return ObjectCounter.ATTRIBUTES;
    }

    @Override
    public Object getAttribute(int index) {
        switch (index) {
            case TYPE:
                return this.type;
            case OBJECT_NUMBER:
                return this.objectNumber;
            default:
                return null;
        }
    }

    private void count() throws ProgramException {
        Persistent loadedCounter = Main.getController().getPersistence().loadCounter(this);
        objectNumber = ((ObjectCounter) loadedCounter).getObjectNumber();
        objectNumber++;
        Main.getController().getPersistence().saveCounter(this);
    }

}
