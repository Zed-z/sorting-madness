package pl.put.poznan.sortingmadness.logic;

/**
 * An object for sorting that represents an integer value
 */
public class SortedObjectInt extends SortedObject {

    private int value;

    /**
     * @param index Object index, to be returned to the client after sorting
     * @param sortingValue The value represented by the object
     */
    public SortedObjectInt(int index, int sortingValue) {
        super(index);
        value = sortingValue;
    }

    /**
     * @return The value represented by the object
     */
    public int getValue() {
        return value;
    }
}
