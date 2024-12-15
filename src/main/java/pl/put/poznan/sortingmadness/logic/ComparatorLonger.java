package pl.put.poznan.sortingmadness.logic;

/**
 * A comparator that causes longer values (by character length) to come first
 * @param <T> The type of SortedObject to compare
 */
public class ComparatorLonger<T extends SortedObject> implements Comparator<T>  {
    @Override
    public int compare(T a, T b) {
        if (a instanceof SortedObjectInt && b instanceof SortedObjectInt) {
            if (String.valueOf(((SortedObjectInt) a).getValue()).length() > String.valueOf(((SortedObjectInt) b).getValue()).length()) return -1;
            if (String.valueOf(((SortedObjectInt) a).getValue()).length() < String.valueOf(((SortedObjectInt) b).getValue()).length()) return 1;
            if (String.valueOf(((SortedObjectInt) a).getValue()).length() == String.valueOf(((SortedObjectInt) b).getValue()).length()) return 0;
        } else if (a instanceof SortedObjectDouble && b instanceof SortedObjectDouble) {
            if (String.valueOf(((SortedObjectDouble) a).getValue()).length() > String.valueOf(((SortedObjectDouble) b).getValue()).length()) return -1;
            if (String.valueOf(((SortedObjectDouble) a).getValue()).length() < String.valueOf(((SortedObjectDouble) b).getValue()).length()) return 1;
            if (String.valueOf(((SortedObjectDouble) a).getValue()).length() == String.valueOf(((SortedObjectDouble) b).getValue()).length()) return 0;
        } else if (a instanceof SortedObjectString && b instanceof SortedObjectString) {
            if (((SortedObjectString) a).getValue().length() > (((SortedObjectString) b).getValue()).length()) return -1;
            if (((SortedObjectString) a).getValue().length() < (((SortedObjectString) b).getValue()).length()) return 1;
            if (((SortedObjectString) a).getValue().length() == (((SortedObjectString) b).getValue()).length()) return 0;
        }
        throw new IllegalArgumentException();
    }
}
