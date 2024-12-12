package pl.put.poznan.transformer.logic;

public class SortingStrategyMergeSort implements SortingStrategy {
    public SortingStrategyMergeSort() {
        super();
    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {
        int n = objects.length;
        mergeSort(objects, n, comparator);
    }

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

    public static void merge(
            SortedObject[] objects, SortedObject[] l, SortedObject[] r, int left, int right, Comparator comparator) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (comparator.compare(l[i], r[j])) {
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
