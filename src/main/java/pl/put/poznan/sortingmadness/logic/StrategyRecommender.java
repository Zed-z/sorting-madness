package pl.put.poznan.sortingmadness.logic;

import java.util.List;

public class StrategyRecommender {
    /**
     * A function to recommend the sorting strategy to use base on data type and amount
     * @return The name of the sort algorithm to use
     */
    public static String recommendStrategy(Class sortedObjectClass, List<Object> objects) {
        if (sortedObjectClass == SortedObjectString.class) {
            return "MergeSort";
        }

        if (objects.size() <= 10) {
            return "InsertionSort";
        } else if (objects.size() <= 100) {
            return "QuickSort";
        } else {
            return "HeapSort";
        }
    }
}
