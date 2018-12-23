/*
 * Counts every created commonObject
 */
package core;

import core.persistence.Persistence;
import core.persistence.Persistent;

/**
 *
 * @author Ricard P. Barnes
 */
public final class ObjectCounter implements Persistent {

    private static final String ID = "counter";

    private Class type;
    private Persistent loadedCounter;
    private int objectNumber;
    private String id;

    public ObjectCounter(Class pType) throws ProgramException {
        type = pType;
        count();
    }

    /**
     * Instanced as example class
     *
     * @param str
     */
    public ObjectCounter(String str) {
        id = ID;
        objectNumber = 0;
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
        return 0;
    }

    @Override
    public String getAttribute(int index) {
        return null;
    }

    private void count() throws ProgramException {
        loadedCounter = Persistence.loadCounter(this);
        objectNumber = ((ObjectCounter) loadedCounter).getObjectNumber();
        objectNumber++;
        Persistence.saveCounter(this);
    }

}
