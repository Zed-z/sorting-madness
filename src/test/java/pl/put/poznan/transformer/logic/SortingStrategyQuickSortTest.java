package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortingStrategyQuickSortTest {

    @Test
    void sortGreaterOrEqual() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 12),
                new SortedObjectInt(1, 16),
                new SortedObjectInt(2, 1),
                new SortedObjectInt(3, 6)
        };

        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorGreaterOrEqual();
        sortingStrategy.sort(sortedObjects, comparator);

        assertEquals(1, sortedObjects[0].getValue());
        assertEquals(6, sortedObjects[1].getValue());
        assertEquals(12, sortedObjects[2].getValue());
        assertEquals(16, sortedObjects[3].getValue());
    }

    @Test
    void sortLesserOrEqual() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 12),
                new SortedObjectInt(1, 16),
                new SortedObjectInt(2, 1),
                new SortedObjectInt(3, 6)
        };

        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorLesserOrEqual();
        sortingStrategy.sort(sortedObjects, comparator);

        assertEquals(16, sortedObjects[0].getValue());
        assertEquals(12, sortedObjects[1].getValue());
        assertEquals(6, sortedObjects[2].getValue());
        assertEquals(1, sortedObjects[3].getValue());
    }

    @Test
    void sortShorterOrEqual() {
        SortedObjectString[] sortedObjects = new SortedObjectString[]{
                new SortedObjectString(0, "a"),
                new SortedObjectString(1, "aa"),
                new SortedObjectString(2, "aaa"),
                new SortedObjectString(3, "aaaa")
        };

        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorShorterOrEqual();
        sortingStrategy.sort(sortedObjects, comparator);


        assertEquals("aaaa", sortedObjects[0].getValue());
        assertEquals("aaa", sortedObjects[1].getValue());
        assertEquals("aa", sortedObjects[2].getValue());
        assertEquals("a", sortedObjects[3].getValue());
    }

    @Test
    void sortLongerOrEqual() {
        SortedObjectString[] sortedObjects = new SortedObjectString[]{
                new SortedObjectString(0, "aaaa"),
                new SortedObjectString(1, "aaaaaaa"),
                new SortedObjectString(2, "aa"),
                new SortedObjectString(3, "aaaaa")
        };

        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorLongerOrEqual();
        sortingStrategy.sort(sortedObjects, comparator);


        assertEquals("aa", sortedObjects[0].getValue());
        assertEquals("aaaa", sortedObjects[1].getValue());
        assertEquals("aaaaa", sortedObjects[2].getValue());
        assertEquals("aaaaaaa", sortedObjects[3].getValue());
    }

}