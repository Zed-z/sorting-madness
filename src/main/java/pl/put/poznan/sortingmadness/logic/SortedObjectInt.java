package pl.put.poznan.sortingmadness.logic;

/**
 * An object for sorting that represents an integer value
 */
public class SortedObjectInt extends SortedObject {
    /**
     * The value represented by the object
     */
    private int value;

    /**
     * Constructor for the SortedObjectInt
     * @param index Object index, to be returned to the client after sorting
     * @param sortingValue The value represented by the object
     */
    public SortedObjectInt(int index, int sortingValue) {
        super(index);
        value = sortingValue;
    }

    /**
     * Getter for the value
     * @return The value represented by the object
     */
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<" + index + ":" + value + ">";
    }
}
