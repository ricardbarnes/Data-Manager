/*
 * Objects that will be stored in a relational way
 */
package core.persistence;

/**
 *
 * @author Ricard P. Barnes
 */
public interface Relational {
    
    public String getColumnName(int index);
    public void setAttributes(int index, Object value);
    
}
