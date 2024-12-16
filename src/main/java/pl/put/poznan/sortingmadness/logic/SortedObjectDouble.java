package pl.put.poznan.sortingmadness.logic;

/**
 * An object for sorting that represents a double / float value
 */
public class SortedObjectDouble extends SortedObject {

    private double value;

    /**
     * @param index Object index, to be returned to the client after sorting
     * @param sortingValue The value represented by the object
     */
    public SortedObjectDouble(int index, double sortingValue) {
        super(index);
        value = sortingValue;
    }

    /**
     * @return The value represented by the object
     */
    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<" + index + ":" + value + ">";
    }
}