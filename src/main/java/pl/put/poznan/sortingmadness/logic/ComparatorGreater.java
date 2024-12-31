package pl.put.poznan.sortingmadness.logic;

/**
 * A comparator that causes greater values to come first
 * @param <T> The type of SortedObject to compare
 */
public class ComparatorGreater<T extends SortedObject> implements Comparator<T> {
    @Override
    public int compare(T a, T b) {
        if (a instanceof SortedObjectInt && b instanceof SortedObjectInt) {
            int valueA = ((SortedObjectInt) a).getValue();
            int valueB = ((SortedObjectInt) b).getValue();
            if (valueA > valueB) return -1;
            if (valueA < valueB) return 1;
            if (valueA == valueB) return 0;
        } else if (a instanceof SortedObjectDouble && b instanceof SortedObjectDouble) {
            double valueA = ((SortedObjectDouble) a).getValue();
            double valueB = ((SortedObjectDouble) b).getValue();
            if (valueA > valueB) return -1;
            if (valueA < valueB) return 1;
            if (valueA == valueB) return 0;
        } else if (a instanceof SortedObjectString && b instanceof SortedObjectString) {
            String valueA = ((SortedObjectString) a).getValue();
            String valueB = ((SortedObjectString) b).getValue();
            if (valueA.compareToIgnoreCase(valueB) < 0) return -1;
            if (valueA.compareToIgnoreCase(valueB) > 0) return 1;
            if (valueA.compareToIgnoreCase(valueB) == 0) return 0;
        }
        throw new IllegalArgumentException();
    }
}
