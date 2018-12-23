/*
 * Persistence provider overclass
 */
package core.commons;

import core.ObjectCounter;
import core.ProgramException;
import core.persistence.Persistent;

/**
 *
 * @author Ricard P. Barnes
 */
public abstract class CommonObject implements Persistent {

    protected String id;

    protected void build() throws ProgramException {
        id = this.getClass().getSimpleName() + Standard.ID_SEPARATOR + String.valueOf(new ObjectCounter(this.getClass()).getObjectNumber());
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        id = this.getClass().getSimpleName() + "#" + id;
        this.id = id;
    }

}
