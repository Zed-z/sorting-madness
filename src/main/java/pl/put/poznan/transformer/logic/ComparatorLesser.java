package pl.put.poznan.transformer.logic;

public class ComparatorLesser<T extends SortedObject> implements Comparator<T> {
    @Override
    public boolean compare(T a, T b) {
        if (a instanceof SortedObjectInt && b instanceof SortedObjectInt) {
            return ((SortedObjectInt) a).getValue() < ((SortedObjectInt) b).getValue();
        } else if (a instanceof SortedObjectString && b instanceof SortedObjectString) {
            return ((SortedObjectString) a).getValue().compareToIgnoreCase(((SortedObjectString) b).getValue()) < 0;
        }
        throw new IllegalArgumentException();
    }
}
