package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
/**
 * Test suite for the Insertion Sort algorithm
 */
class SortingStrategyInsertionSortTest {

    /**
     * Test to check sorting with the Greater comparator
     */
    @Test
    void sortGreater() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 12),
                new SortedObjectInt(1, 16),
                new SortedObjectInt(2, 1),
                new SortedObjectInt(3, 6)
        };

        SortingStrategy sortingStrategy = new SortingStrategyInsertionSort();
        Comparator comparator = new ComparatorGreater();
        sortingStrategy.sort(sortedObjects, comparator);

        assertEquals(16, sortedObjects[0].getValue());
        assertEquals(12, sortedObjects[1].getValue());
        assertEquals(6, sortedObjects[2].getValue());
        assertEquals(1, sortedObjects[3].getValue());
    }

    /**
     * Test to check sorting with the Lesser comparator
     */
    @Test
    void sortLesser() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 12),
                new SortedObjectInt(1, 16),
                new SortedObjectInt(2, 1),
                new SortedObjectInt(3, 6)
        };

        SortingStrategy sortingStrategy = new SortingStrategyInsertionSort();
        Comparator comparator = new ComparatorLesser();
        sortingStrategy.sort(sortedObjects, comparator);

        assertEquals(1, sortedObjects[0].getValue());
        assertEquals(6, sortedObjects[1].getValue());
        assertEquals(12, sortedObjects[2].getValue());
        assertEquals(16, sortedObjects[3].getValue());
    }

    /**
     * Test to check sorting with the Shorter comparator
     */
    @Test
    void sortShorter() {
        SortedObjectString[] sortedObjects = new SortedObjectString[]{
                new SortedObjectString(0, "a"),
                new SortedObjectString(1, "aa"),
                new SortedObjectString(2, "aaa"),
                new SortedObjectString(3, "aaaa")
        };

        SortingStrategy sortingStrategy = new SortingStrategyInsertionSort();
        Comparator comparator = new ComparatorShorter();
        sortingStrategy.sort(sortedObjects, comparator);


        assertEquals("a", sortedObjects[0].getValue());
        assertEquals("aa", sortedObjects[1].getValue());
        assertEquals("aaa", sortedObjects[2].getValue());
        assertEquals("aaaa", sortedObjects[3].getValue());
    }

    /**
     * Test to check sorting with the Longer comparator
     */
    @Test
    void sortLonger() {
        SortedObjectString[] sortedObjects = new SortedObjectString[]{
                new SortedObjectString(0, "aaaa"),
                new SortedObjectString(1, "aaaaaaa"),
                new SortedObjectString(2, "aa"),
                new SortedObjectString(3, "aaaaa")
        };

        SortingStrategy sortingStrategy = new SortingStrategyInsertionSort();
        Comparator comparator = new ComparatorLonger();
        sortingStrategy.sort(sortedObjects, comparator);


        assertEquals("aaaaaaa", sortedObjects[0].getValue());
        assertEquals("aaaaa", sortedObjects[1].getValue());
        assertEquals("aaaa", sortedObjects[2].getValue());
        assertEquals("aa", sortedObjects[3].getValue());
    }

    @Test
    void checkComparisonCount() {
        Comparator comparator = mock(Comparator.class);
        assertEquals(comparator.compare(any(), any()), 0);
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 7),
                new SortedObjectInt(1, 3),
                new SortedObjectInt(2, 5),
                new SortedObjectInt(3, 1)

        };
        SortingStrategyInsertionSort sortingStrategy = new SortingStrategyInsertionSort();
        sortingStrategy.sort(sortedObjects, comparator);
        System.out.println(Arrays.toString(sortedObjects));
        verify(comparator, times(4)).compare(any(), any());


    }

    @Test
    void checkComparisonWithSteps() {
        Comparator comparator = mock(Comparator.class);
        assertEquals(comparator.compare(any(), any()), 0);
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 7),
                new SortedObjectInt(1, 3),
                new SortedObjectInt(2, 5),
                new SortedObjectInt(3, 1)

        };
        SortingStrategyInsertionSort sortingStrategy = new SortingStrategyInsertionSort();
        sortingStrategy.sort(sortedObjects, comparator, 1);
        System.out.println(Arrays.toString(sortedObjects));
        verify(comparator, times(3)).compare(any(), any());


    }

    /**
     * Test for a potential edge case where an empty list is given
     */
    @Test
    void emptyEdgeCaseTest() {
        // Random comparator, doesn't matter
        Comparator comparator = mock(Comparator.class);
        when(comparator.compare(any(), any())).thenReturn(0);

        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{};
        SortingStrategy sortingStrategy = new SortingStrategyInsertionSort();
        sortingStrategy.sort(sortedObjects, comparator);

        // Should return empty list
        assertEquals(0, sortedObjects.length);

        // Comaparator shouldn't be used
        verify(comparator, times(0)).compare(any(), any());
    }
}