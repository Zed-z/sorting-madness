package pl.put.poznan.sortingmadness.logic;

public class SortingStrategyInsertionSort implements SortingStrategy {
    public SortingStrategyInsertionSort() {
        super();
    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {
        for (int i = 1; i < objects.length; i++) {
            SortedObject key = objects[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(objects[j], key) > 0) {
                objects[j + 1] = objects[j];
                j--;
            }
            objects[j + 1] = key;
    }
}
}
