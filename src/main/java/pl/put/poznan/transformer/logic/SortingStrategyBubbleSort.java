package pl.put.poznan.transformer.logic;

public class SortingStrategyBubbleSort implements SortingStrategy {
    public SortingStrategyBubbleSort() {
        super();
    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {
        for (int i = 0; i < objects.length - 1; i++) {
            for (int j = i + 1; j < objects.length; j++) {
                if (comparator.compare(objects[i], objects[j]) ) {
                    SortedObject temp = objects[j];
                    objects[j] = objects[j + 1];
                    objects[j + 1] = temp;
                }
            }
        }
    }
}
