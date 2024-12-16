package pl.put.poznan.sortingmadness.logic;

/**
 * An implementation of the Merge Sort algorithm
 */
public class SortingStrategyMergeSort implements SortingStrategy {

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {
        int n = objects.length;
        mergeSort(objects, n, comparator);
    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator, int steps) {
        int n = objects.length;
        mergeSort(objects, n, comparator, steps);
    }

    /**
     * A recursive function to perform the Merge Sort algorithm
     * @param objects An array of objects to sort
     * @param n The size of the array
     * @param comparator The comparator to use when sorting
     */
    public static void mergeSort(SortedObject[] objects, int n, Comparator comparator) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        SortedObject[] l = new SortedObject[mid];
        SortedObject[] r = new SortedObject[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = objects[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = objects[i];
        }

        mergeSort(l, mid, comparator);
        mergeSort(r, n - mid, comparator);

        merge(objects, l, r, mid, n - mid, comparator);
    }

    /**
     * A recursive function to perform the Merge Sort algorithm, with a step limit
     * @param objects An array of objects to sort
     * @param n The size of the array
     * @param comparator The comparator to use when sorting
     * @param steps The step limit
     */
    public static void mergeSort(SortedObject[] objects, int n, Comparator comparator, int steps) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        SortedObject[] l = new SortedObject[mid];
        SortedObject[] r = new SortedObject[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = objects[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = objects[i];
        }
        if(steps-- > 0) {
            mergeSort(l, mid, comparator,steps);
            mergeSort(r, n - mid, comparator,steps);
        }
        merge(objects, l, r, mid, n - mid, comparator);
    }

    /**
     * A function to merge two arrays of sorted objects together
     * @param objects An array of objects to insert into
     * @param l The sorted left input array
     * @param r The sorted right input array
     * @param left The size of the left array
     * @param right The size of the right array
     * @param comparator The comparator to use when sorting
     */
    public static void merge(
            SortedObject[] objects, SortedObject[] l, SortedObject[] r, int left, int right, Comparator comparator) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (comparator.compare(l[i], r[j]) > 0) {
                objects[k++] = r[j++];
            }
            else {
                objects[k++] = l[i++];
            }
        }
        while (i < left) {
            objects[k++] = l[i++];
        }
        while (j < right) {
            objects[k++] = r[j++];
        }
    }

}
