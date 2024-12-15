package pl.put.poznan.sortingmadness.logic;

/**
 * A comparator that causes lesser values to come first
 * @param <T> The type of SortedObject to compare
 */
public class ComparatorLesser<T extends SortedObject> implements Comparator<T> {
    @Override
    public int compare(T a, T b) {
        if (a instanceof SortedObjectInt && b instanceof SortedObjectInt) {
            if (((SortedObjectInt) a).getValue() > ((SortedObjectInt) b).getValue()) return 1;
            if (((SortedObjectInt) a).getValue() < ((SortedObjectInt) b).getValue()) return -1;
            if (((SortedObjectInt) a).getValue() == ((SortedObjectInt) b).getValue()) return 0;
        } else if (a instanceof SortedObjectDouble && b instanceof SortedObjectDouble) {
            if (((SortedObjectDouble) a).getValue() > ((SortedObjectDouble) b).getValue()) return 1;
            if (((SortedObjectDouble) a).getValue() < ((SortedObjectDouble) b).getValue()) return -1;
            if (((SortedObjectDouble) a).getValue() == ((SortedObjectDouble) b).getValue()) return 0;
        } else if (a instanceof SortedObjectString && b instanceof SortedObjectString) {
            if (((SortedObjectString) a).getValue().compareToIgnoreCase(((SortedObjectString) b).getValue()) < 0) return 1;
            if (((SortedObjectString) a).getValue().compareToIgnoreCase(((SortedObjectString) b).getValue()) > 0) return -1;
            if (((SortedObjectString) a).getValue().compareToIgnoreCase(((SortedObjectString) b).getValue()) == 0) return 0;
        }
        throw new IllegalArgumentException();
    }
}
