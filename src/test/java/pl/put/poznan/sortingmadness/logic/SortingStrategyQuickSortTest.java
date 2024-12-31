package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the Quick Sort algorithm
 */
class SortingStrategyQuickSortTest {


    /**
     * Tests to verify compatibility with value based comparator Greater
     */
    @Test
    void sortGreater() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 12),
                new SortedObjectInt(1, 16),
                new SortedObjectInt(2, 1),
                new SortedObjectInt(3, 6)
        };

        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorGreater();
        sortingStrategy.sort(sortedObjects, comparator);

        assertEquals(16, sortedObjects[0].getValue());
        assertEquals(12, sortedObjects[1].getValue());
        assertEquals(6, sortedObjects[2].getValue());
        assertEquals(1, sortedObjects[3].getValue());
    }

    /**
     * Tests  to verify compatibility with value based comparator Lesser
     */
    @Test
    void sortLesser() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 12),
                new SortedObjectInt(1, 16),
                new SortedObjectInt(2, 1),
                new SortedObjectInt(3, 6)
        };

        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorLesser();
        sortingStrategy.sort(sortedObjects, comparator);

        assertEquals(1, sortedObjects[0].getValue());
        assertEquals(6, sortedObjects[1].getValue());
        assertEquals(12, sortedObjects[2].getValue());
        assertEquals(16, sortedObjects[3].getValue());
    }

    /**
     * Tests  to verify compatibility with string length comparator Shorter
     */
    @Test
    void sortShorter() {
        SortedObjectString[] sortedObjects = new SortedObjectString[]{
                new SortedObjectString(0, "a"),
                new SortedObjectString(1, "aa"),
                new SortedObjectString(2, "aaa"),
                new SortedObjectString(3, "aaaa")
        };

        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorShorter();
        sortingStrategy.sort(sortedObjects, comparator);


        assertEquals("a", sortedObjects[0].getValue());
        assertEquals("aa", sortedObjects[1].getValue());
        assertEquals("aaa", sortedObjects[2].getValue());
        assertEquals("aaaa", sortedObjects[3].getValue());
    }

    /**
     * Tests to verify compatibility with string length comparator Longer
     */
    @Test
    void sortLonger() {
        SortedObjectString[] sortedObjects = new SortedObjectString[]{
                new SortedObjectString(0, "aaaa"),
                new SortedObjectString(1, "aaaaaaa"),
                new SortedObjectString(2, "aa"),
                new SortedObjectString(3, "aaaaa")
        };

        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorLonger();
        sortingStrategy.sort(sortedObjects, comparator);


        assertEquals("aaaaaaa", sortedObjects[0].getValue());
        assertEquals("aaaaa", sortedObjects[1].getValue());
        assertEquals("aaaa", sortedObjects[2].getValue());
        assertEquals("aa", sortedObjects[3].getValue());
    }

    @Test
    void SortAlreadySorted(){
        SortedObjectString[] sortedObjects = new SortedObjectString[]{
                new SortedObjectString(0, "aa"),
                new SortedObjectString(1, "aaa"),
                new SortedObjectString(2, "aaaa"),
                new SortedObjectString(3, "aaaaa")
        };


        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorGreater();
        sortingStrategy.sort(sortedObjects, comparator);


        assertEquals("aa", sortedObjects[0].getValue());
        assertEquals("aaa", sortedObjects[1].getValue());
        assertEquals("aaaa", sortedObjects[2].getValue());
        assertEquals("aaaaa", sortedObjects[3].getValue());

    }

    @Test
    void SortWithRandom(){
        SortedObjectString[] sortedObjects = new SortedObjectString[]{
                new SortedObjectString(0, "aaaa"),
                new SortedObjectString(1, "aaaaaaa"),
                new SortedObjectString(2, "aa"),
                new SortedObjectString(3, "aaaaa")
        };


        SortingStrategy sortingStrategy = new SortingStrategyQuickSort();
        Comparator comparator = new ComparatorRandom();
        assertDoesNotThrow(

                () -> {
                    sortingStrategy.sort(sortedObjects, comparator);
                }
        );

    }



}