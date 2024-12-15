package pl.put.poznan.sortingmadness.logic;

/**
 * An implementation of the Bubble Sort algorithm
 */
public class SortingStrategyBubbleSort implements SortingStrategy {

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects.length; j++) {
                if (comparator.compare(objects[j], objects[i]) > 0) {
                    SortedObject temp = objects[j];
                    objects[j] = objects[i];
                    objects[i] = temp;
                }
            }
        }
    }
}
