package pl.put.poznan.sortingmadness.logic;

/**
 * An object for sorting that represents a string of characters
 */
public class SortedObjectString extends SortedObject {
    /**
     * The value represented by the object
     */
    public String value;

    /**
     * Constructor for the SortedObjectString
     * @param index Object index, to be returned to the client after sorting
     * @param sortingValue The string represented by the object
     */
    public SortedObjectString(int index, String sortingValue) {
        super(index);
        value = sortingValue;
    }

    /**
     * Getter for the value
     * @return The value represented by the object
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<" + index + ":" + value + ">";
    }
}
