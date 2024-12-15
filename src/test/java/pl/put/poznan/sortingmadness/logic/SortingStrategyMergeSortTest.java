package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortingStrategyMergeSortTest {

    @Test void sortGreater() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 7),
                new SortedObjectInt(1, 3),
                new SortedObjectInt(2, 5),
                new SortedObjectInt(3, 1)
        };

        SortingStrategy sortingStrategy = new SortingStrategyMergeSort();
        Comparator comparator = new ComparatorGreater();

        sortingStrategy.sort(sortedObjects, comparator);

        System.out.println(Arrays.toString(sortedObjects));

        assertEquals(7, sortedObjects[0].getValue());
        assertEquals(5, sortedObjects[1].getValue());
        assertEquals(3, sortedObjects[2].getValue());
        assertEquals(1, sortedObjects[3].getValue());
    }

    @Test void sortLess() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 7),
                new SortedObjectInt(1, 3),
                new SortedObjectInt(2, 5),
                new SortedObjectInt(3, 1)
        };

        SortingStrategy sortingStrategy = new SortingStrategyMergeSort();
        Comparator comparator = new ComparatorLesser();

        sortingStrategy.sort(sortedObjects, comparator);

        System.out.println(Arrays.toString(sortedObjects));

        assertEquals(1, sortedObjects[0].getValue());
        assertEquals(3, sortedObjects[1].getValue());
        assertEquals(5, sortedObjects[2].getValue());
        assertEquals(7, sortedObjects[3].getValue());
    }

}