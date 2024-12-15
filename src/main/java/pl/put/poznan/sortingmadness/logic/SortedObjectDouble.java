package pl.put.poznan.sortingmadness.logic;

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