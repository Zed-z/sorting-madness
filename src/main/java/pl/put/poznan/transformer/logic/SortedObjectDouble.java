package pl.put.poznan.transformer.logic;

public class SortedObjectDouble extends SortedObject {

    private double value;

    public SortedObjectDouble(int index, double sortingValue) {
        super(index);
        value = sortingValue;
    }

    public double getValue() {
        return value;
    }
}