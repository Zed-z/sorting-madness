package pl.put.poznan.sortingmadness.logic;

public interface Comparator<T extends SortedObject> {
    int compare(T a, T b);
}
