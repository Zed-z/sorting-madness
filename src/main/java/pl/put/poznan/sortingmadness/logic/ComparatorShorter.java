package pl.put.poznan.sortingmadness.logic;

/**
 * A comparator that causes shorter values (by character length) to come first
 * @param <T> The type of SortedObject to compare
 */
public class ComparatorShorter<T extends SortedObject> implements Comparator<T> {
    @Override
    public int compare(T a, T b) {
        if (a instanceof SortedObjectInt && b instanceof SortedObjectInt) {
            int valueA = String.valueOf(((SortedObjectInt) a).getValue()).length();
            int valueB = String.valueOf(((SortedObjectInt) b).getValue()).length();
            if (valueA > valueB) return 1;
            if (valueA < valueB) return -1;
            if (valueA == valueB) return 0;
        } else if (a instanceof SortedObjectDouble && b instanceof SortedObjectDouble) {
            int valueA = String.valueOf(((SortedObjectDouble) a).getValue()).length();
            int valueB = String.valueOf(((SortedObjectDouble) b).getValue()).length();
            if (valueA > valueB) return 1;
            if (valueA < valueB) return -1;
            if (valueA == valueB) return 0;
        } else if (a instanceof SortedObjectString && b instanceof SortedObjectString) {
            int valueA = ((SortedObjectString) a).getValue().length();
            int valueB = ((SortedObjectString) b).getValue().length();
            if (valueA > valueB) return 1;
            if (valueA < valueB) return -1;
            if (valueA == valueB) return 0;
        }
        throw new IllegalArgumentException();
    }
}
