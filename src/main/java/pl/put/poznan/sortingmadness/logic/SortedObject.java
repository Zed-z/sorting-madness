package pl.put.poznan.sortingmadness.logic;

/**
 * Represents an abstract object to be sorted by algorithms
 */
public abstract class SortedObject {
    /**
     * Object index, to be returned to the client after sorting
     */
    int index;

    /**
     * A constructor for the SortedObject class
     * @param index Object index, to be returned to the client after sorting
     */
    public SortedObject(int index) {
        this.index = index;
    }

    /**
     * Getter for the index
     * @return The index value of the object
     */
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "<" + index + ">";
    }
}

