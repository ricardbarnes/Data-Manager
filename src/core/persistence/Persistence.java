/*
 * Persistence mother class
 */
package core.persistence;

import core.ObjectCounter;
import core.ProgramException;
import core.commons.CommonObject;
import java.util.List;

/**
 *
 * @author Ricard P. Barnes
 */
public abstract class Persistence {
    
    public abstract void save(Persistent aThis) throws ProgramException;
    public abstract Persistent load(Persistent aThis) throws ProgramException;
    public abstract void delete(Persistent aThis) throws ProgramException;
    public abstract List<Persistent> getAll(Persistent example) throws ProgramException;
    public abstract void saveCounter(ObjectCounter counter) throws ProgramException;
    public abstract ObjectCounter loadCounter(ObjectCounter counter) throws ProgramException;
    
}
