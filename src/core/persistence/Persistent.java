/*
 * Basic interface for persistent objects
 */
package core.persistence;

/**
 *
 * @author Ricard P. Barnes
 */
public interface Persistent {
    
    public void setId(String str);
    public String getId();
    public int getAttributeNumber();
    public Object getAttribute(int index);
    
}
