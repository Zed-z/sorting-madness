package pl.put.poznan.transformer.logic;

public class ComparatorShorterOrEqual<T extends SortedObject> implements Comparator<T> {
    @Override
    public boolean compare(T a, T b) {
        if (a instanceof SortedObjectInt && b instanceof SortedObjectInt) {
            return String.valueOf(((SortedObjectInt) a).getValue()).length() <= String.valueOf(((SortedObjectInt) b).getValue()).length();
        } else if (a instanceof SortedObjectString && b instanceof SortedObjectString) {
            return ((SortedObjectString) a).getValue().length() <= (((SortedObjectString) b).getValue()).length();
        }
        throw new IllegalArgumentException();
    }
}