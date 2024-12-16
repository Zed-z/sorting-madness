package pl.put.poznan.sortingmadness.logic;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
}
