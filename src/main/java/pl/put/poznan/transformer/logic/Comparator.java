package pl.put.poznan.transformer.logic;

interface Comparator<T extends SortedObject> {
    boolean compare(T a, T b);
}
