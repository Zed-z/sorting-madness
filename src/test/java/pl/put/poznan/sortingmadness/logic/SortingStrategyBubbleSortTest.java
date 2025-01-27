package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;
/**
 * A test suite for testing the Bubble Sort algorithm
 */
class SortingStrategyBubbleSortTest {

    /**
     * A test to check correct sorting when using the Greater comparator
     */
    @Test void sortGreater() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 7),
                new SortedObjectInt(1, 3),
                new SortedObjectInt(2, 5),
                new SortedObjectInt(3, 1)
        };

        SortingStrategy sortingStrategy = new SortingStrategyBubbleSort();
        Comparator comparator = new ComparatorGreater();

        sortingStrategy.sort(sortedObjects, comparator);

        System.out.println(Arrays.toString(sortedObjects));

        assertEquals(7, sortedObjects[0].getValue());
        assertEquals(5, sortedObjects[1].getValue());
        assertEquals(3, sortedObjects[2].getValue());
        assertEquals(1, sortedObjects[3].getValue());
    }

    /**
     * A test to check correct sorting when using the Lesser comparator
     */
    @Test void sortLess() {
        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 7),
                new SortedObjectInt(1, 3),
                new SortedObjectInt(2, 5),
                new SortedObjectInt(3, 1)
        };

        SortingStrategy sortingStrategy = new SortingStrategyBubbleSort();
        Comparator comparator = new ComparatorLesser();

        sortingStrategy.sort(sortedObjects, comparator);

        System.out.println(Arrays.toString(sortedObjects));

        assertEquals(1, sortedObjects[0].getValue());
        assertEquals(3, sortedObjects[1].getValue());
        assertEquals(5, sortedObjects[2].getValue());
        assertEquals(7, sortedObjects[3].getValue());
    }


    /**
     * Test to check if comparator has been used enough times
     */
    @Test
    void checkComparisonCount() {

        Comparator comparator = mock(Comparator.class);

        when(comparator.compare(any(), any())).thenReturn(0);




        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 7),
                new SortedObjectInt(1, 3),
                new SortedObjectInt(2, 5),
                new SortedObjectInt(3, 1)
        };

        SortingStrategy sortingStrategy = new SortingStrategyBubbleSort();


        sortingStrategy.sort(sortedObjects, comparator);

        System.out.println(Arrays.toString(sortedObjects));



        verify(comparator, times(16)).compare(any(), any());

    }


    /**
     * Test to check if comparator has been used enough times
     * This time with a step limit
     */
    @Test
    void checkComparisonCountWithSteps() {

        Comparator comparator = mock(Comparator.class);

        when(comparator.compare(any(), any())).thenReturn(0);




        SortedObjectInt[] sortedObjects = new SortedObjectInt[]{
                new SortedObjectInt(0, 7),
                new SortedObjectInt(1, 3),
                new SortedObjectInt(2, 5),
                new SortedObjectInt(3, 1)
        };

        SortingStrategy sortingStrategy = new SortingStrategyBubbleSort();


        sortingStrategy.sort(sortedObjects, comparator,1);

        System.out.println(Arrays.toString(sortedObjects));



        verify(comparator, times(8)).compare(any(), any());

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
        SortingStrategy sortingStrategy = new SortingStrategyBubbleSort();
        sortingStrategy.sort(sortedObjects, comparator);

        // Should return empty list
        assertEquals(0, sortedObjects.length);

        // Comaparator shouldn't be used
        verify(comparator, times(0)).compare(any(), any());
    }
}