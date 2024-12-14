package pl.put.poznan.transformer.logic;

public interface Comparator<T extends SortedObject> {
    boolean compare(T a, T b);
}
