package pl.put.poznan.transformer.logic;

public class SortedObjectInt extends SortedObject {

    private int value;

    public SortedObjectInt(int index, int sortingValue) {
        super(index);
        value = sortingValue;
    }

    public int getValue() {
        return value;
    }
}
