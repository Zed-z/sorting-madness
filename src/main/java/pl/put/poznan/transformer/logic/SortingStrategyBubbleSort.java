package pl.put.poznan.transformer.logic;

public class SortingStrategyBubbleSort implements SortingStrategy {
    public SortingStrategyBubbleSort() {
        super();
    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects.length; j++) {
                if (comparator.compare(objects[j], objects[i])) {
                    SortedObject temp = objects[j];
                    objects[j] = objects[i];
                    objects[i] = temp;
                }
            }
        }
    }
}
