package pl.put.poznan.sortingmadness.logic;

/**
 * Defines an interface for comparators to be used by sorting algorithms
 * @param <T> The type of SortedObject to compare
 */
public interface Comparator<T extends SortedObject> {
    /**
     * A comparison function to be used by sorting algorithms
     * @param a The first object
     * @param b The second object
     * @return -1 if object a should be on the left, 1 if object a should be on the right, 0 if objects are equal
     */
    int compare(T a, T b);
}
