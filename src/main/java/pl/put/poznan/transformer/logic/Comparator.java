package pl.put.poznan.transformer.logic;

public interface Comparator<T extends SortedObject> {
    int compare(T a, T b);
}
