package pl.put.poznan.sortingmadness.logic;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Test suite for testing the Select Sort algorithm
 */
public class SortingStrategySelectSortTest {

    /**
    * test to verify compatibility with ComparatorGreater, best case data
     */
    @Test
    public void sortGreaterBestCase(){
        SortedObjectInt[] objects = new SortedObjectInt[]{
                new SortedObjectInt(0,0),
                new SortedObjectInt(1,1),
                new SortedObjectInt(2,2),
                new SortedObjectInt(3,3),
        };
        SortingStrategy s = new SortingStrategySelectSort();
        Comparator c = new ComparatorGreater();

        s.sort(objects, c);

        assertEquals(3, objects[0].getValue());
        assertEquals(2, objects[1].getValue());
        assertEquals(1, objects[2].getValue());
        assertEquals(0, objects[3].getValue());

    }
    /**
     * test to verify compatibility with ComparatorGreater, worst case data
     */
    @Test
    public void sortGreaterWorstCase(){
        SortedObjectInt[] objects = new SortedObjectInt[]{
                new SortedObjectInt(0,3),
                new SortedObjectInt(1,2),
                new SortedObjectInt(2,1),
                new SortedObjectInt(3,0),
        };
        SortingStrategy s = new SortingStrategySelectSort();
        Comparator c = new ComparatorGreater();

        s.sort(objects, c);

        assertEquals(3, objects[0].getValue());
        assertEquals(2, objects[1].getValue());
        assertEquals(1, objects[2].getValue());
        assertEquals(0, objects[3].getValue());
    }
    /**
     * test to verify compatibility with ComparatorLesser, worst case data
     */
    @Test
    public void sortLesserWorstCase(){
        SortedObjectInt[] objects = new SortedObjectInt[]{
                new SortedObjectInt(0,0),
                new SortedObjectInt(1,1),
                new SortedObjectInt(2,2),
                new SortedObjectInt(3,3),
        };
        SortingStrategy s = new SortingStrategySelectSort();
        Comparator c = new ComparatorLesser();

        s.sort(objects, c);

        assertEquals(0, objects[0].getValue());
        assertEquals(1, objects[1].getValue());
        assertEquals(2, objects[2].getValue());
        assertEquals(3, objects[3].getValue());
    }
    /**
     * test to verify compatibility with ComparatorLesser, data A-shaped (first growing then falling)
     */
    @Test
    public void sortLesserAshaped(){
        SortedObjectInt[] objects = new SortedObjectInt[]{
                new SortedObjectInt(0,0),
                new SortedObjectInt(1,3),
                new SortedObjectInt(2,2),
                new SortedObjectInt(3,1),
        };
        SortingStrategy s = new SortingStrategySelectSort();
        Comparator c = new ComparatorLesser();

        s.sort(objects, c);

        assertEquals(0, objects[0].getValue());
        assertEquals(1, objects[1].getValue());
        assertEquals(2, objects[2].getValue());
        assertEquals(3, objects[3].getValue());
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

        SortingStrategy sortingStrategy = new SortingStrategySelectSort();


        sortingStrategy.sort(sortedObjects, comparator);

        System.out.println(Arrays.toString(sortedObjects));



        verify(comparator, times(10)).compare(any(), any());

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

        SortingStrategy sortingStrategy = new SortingStrategySelectSort();


        sortingStrategy.sort(sortedObjects, comparator,1);

        System.out.println(Arrays.toString(sortedObjects));



        verify(comparator, times(7)).compare(any(), any());

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
        SortingStrategy sortingStrategy = new SortingStrategySelectSort();
        sortingStrategy.sort(sortedObjects, comparator);

        // Should return empty list
        assertEquals(0, sortedObjects.length);

        // Comaparator shouldn't be used
        verify(comparator, times(0)).compare(any(), any());
    }
}
