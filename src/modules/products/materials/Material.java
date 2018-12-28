/*
 * Material (soldable) abstract class
 */
package modules.products.materials;

import core.ProgramException;
import core.commons.Standard;
import core.commons.CommonObject;
import core.persistence.Relational;

/**
 *
 * @author Ricard P. Barnes
 */
public final class Material extends CommonObject implements Relational {

    // Public indexes
    public static final int ID = 0;
    public static final int DESC = 1;
    public static final int PRICE = 2;
    public static final int UNITY = 3;
    public static final int VALUE_A = 4;
    public static final int VALUE_B = 5;

    private static final int ATTRIBUTES = 6; // Atrribute number

    protected String description;
    protected float price;
    protected String unity; // Applied to unity (ex.: m, m2, u...)
    protected float value_a;
    protected float value_b;
    
    /**
     * Query example constructor
     *
     * @param str Recommended some autodescriptive tag
     */
    public Material(String str) {
    }

    /**
     * Attribute based constructor.
     *
     * @param attributes String array containing the object attributes
     * @throws ProgramException
     */
    public Material(String[] attributes) throws ProgramException {
        super.build();
        setAttributes(attributes);
    }

    /**
     * Used to search the material via it's code (query by example paradigma)
     *
     * @param pCode
     */
    public Material(int pCode) {
        id = this.getClass().getSimpleName() + Standard.ID_SEPARATOR + String.valueOf(pCode);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public float getValue_a() {
        return value_a;
    }

    public void setValue_a(float value_a) {
        this.value_a = value_a;
    }

    public float getValue_b() {
        return value_b;
    }

    public void setValue_b(float value_b) {
        this.value_b = value_b;
    }

    public void setAttributes(String[] parameters) {
        description = parameters[modules.products.materials.Index.Form.Add.TextFields.DESC];
        price = Float.valueOf(parameters[modules.products.materials.Index.Form.Add.TextFields.PRICE]);
        unity = parameters[modules.products.materials.Index.Form.Add.TextFields.UNITY];
        value_a = Float.valueOf(parameters[modules.products.materials.Index.Form.Add.TextFields.VALUE_A]);
        value_b = Float.valueOf(parameters[modules.products.materials.Index.Form.Add.TextFields.VALUE_B]);
    }

    @Override
    public Object getAttribute(int index) {
        switch (index) {
            case ID:
                return this.id;
            case DESC:
                return this.description;
            case PRICE:
                return this.price;
            case UNITY:
                return this.unity;
            case VALUE_A:
                return this.value_a;
            case VALUE_B:
                return this.value_b;
            default:
                return null;
        }
    }

    @Override
    public int getAttributeNumber() {
        return Material.ATTRIBUTES;
    }

    @Override
    public String getColumnName(int index) {
        switch (index) {
            case ID:
                return "ID";
            case DESC:
                return "DESCRIPTION";
            case PRICE:
                return "PRICE";
            case UNITY:
                return "UNITY";
            case VALUE_A:
                return "VALUE_A";
            case VALUE_B:
                return "VALUE_B";
            default:
                return null;
        }
    }

    @Override
    public void setAttributes(int index, Object value) {
        switch (index) {
            case ID:
                this.id = String.valueOf(value);
                break;
            case DESC:
                this.description = String.valueOf(value);
                break;
            case PRICE:
                this.price = Float.parseFloat(String.valueOf(value));
                break;
            case UNITY:
                this.unity = String.valueOf(value);
                break;
            case VALUE_A:
                this.value_a = Float.parseFloat(String.valueOf(value));
                break;
            case VALUE_B:
                this.value_b = Float.parseFloat(String.valueOf(value));
                break;
        }
    }

}
