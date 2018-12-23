/*
 * Material (soldable) abstract class
 */
package modules.products.material;

import core.ObjectCounter;
import core.ProgramException;
import core.commons.Standard;
import core.commons.CommonObject;

/**
 *
 * @author Ricard P. Barnes
 */
public final class Material extends CommonObject {

    public static final int CODE = 0;
    public static final int DESC = 1;
    public static final int PRICE = 2;
    public static final int UNITY = 3;
    public static final int VALUE_A = 4;
    public static final int VALUE_B = 5;

    private static final int ATTRIBUTES = 6;

    protected String code;
    protected String description;
    protected float price;
    protected String unity; // Applied to unity (ex.: m, m2, u...)
    protected float value_a;
    protected float value_b;

    public Material(String[] attributes) throws ProgramException {
        super.build();
        setAttributes(attributes);
    }

    public Material(String str) { // Example   

    }

    public Material(int pCode) {
        id = this.getClass().getSimpleName() + Standard.ID_SEPARATOR + String.valueOf(pCode);
        code = String.valueOf(pCode);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        code = id;
        description = parameters[modules.products.material.Index.Form.Add.TextFields.DESC];
        price = Float.valueOf(parameters[modules.products.material.Index.Form.Add.TextFields.PRICE]);
        unity = parameters[modules.products.material.Index.Form.Add.TextFields.UNITY];
        value_a = Float.valueOf(parameters[modules.products.material.Index.Form.Add.TextFields.VALUE_A]);
        value_b = Float.valueOf(parameters[modules.products.material.Index.Form.Add.TextFields.VALUE_B]);
    }

    @Override
    public String getAttribute(int index) {
        switch (index) {
            case CODE:
                return this.code;
            case DESC:
                return this.description;
            case PRICE:
                return String.valueOf(this.price);
            case UNITY:
                return this.unity;
            case VALUE_A:
                return String.valueOf(this.value_a);
            case VALUE_B:
                return String.valueOf(this.value_b);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Material{"
                + "code=" + code + Standard.TO_STRING_SEPARATOR
                + "description=" + description + Standard.TO_STRING_SEPARATOR
                + "unity=" + unity + Standard.TO_STRING_SEPARATOR
                + "value_a=" + value_a + Standard.TO_STRING_SEPARATOR
                + "value_b=" + value_b + '}';
    }

    @Override
    public int getAttributeNumber() {
        return Material.ATTRIBUTES;
    }

}
