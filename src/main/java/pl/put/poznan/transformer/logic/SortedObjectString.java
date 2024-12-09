package pl.put.poznan.transformer.logic;

public class SortedObjectString extends SortedObject {

    public String value;

    public SortedObjectString(int index, String sortingValue) {
        super(index);
        value = sortingValue;
    }

    public String getValue() {
        return value;
    }
}
