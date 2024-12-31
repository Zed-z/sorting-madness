package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test suite for testing ComparatorLesser
 */
class ComparatorLesserTest {

    ComparatorLesser comparatorLesser;

    @BeforeEach
    void setUp() {
        comparatorLesser = new ComparatorLesser();
    }

    /**
     * Equal values, should return 0
     * getValue should run once for each, why waste CPU time?
     */
    @Test
    public void bothEqual() {
        SortedObjectInt mock1 = mock(SortedObjectInt.class);
        when(mock1.getValue()).thenReturn(5);
        SortedObjectInt mock2 = mock(SortedObjectInt.class);
        when(mock2.getValue()).thenReturn(5);

        assertEquals(0, comparatorLesser.compare(mock1, mock2));
        verify(mock1, times(1)).getValue();
        verify(mock2, times(1)).getValue();
    }

    /**
     * First greater, should return 1
     * getValue should run once for each, why waste CPU time?
     */
    @Test
    public void firstGreater() {
        SortedObjectInt mock1 = mock(SortedObjectInt.class);
        when(mock1.getValue()).thenReturn(10);
        SortedObjectInt mock2 = mock(SortedObjectInt.class);
        when(mock2.getValue()).thenReturn(5);

        assertEquals(1, comparatorLesser.compare(mock1, mock2));
        verify(mock1, times(1)).getValue();
        verify(mock2, times(1)).getValue();
    }

    /**
     * Second greater, should return -1
     * getValue should run once for each, why waste CPU time?
     */
    @Test
    public void secondGreater() {
        SortedObjectInt mock1 = mock(SortedObjectInt.class);
        when(mock1.getValue()).thenReturn(5);
        SortedObjectInt mock2 = mock(SortedObjectInt.class);
        when(mock2.getValue()).thenReturn(10);

        assertEquals(-1, comparatorLesser.compare(mock1, mock2));
        verify(mock1, times(1)).getValue();
        verify(mock2, times(1)).getValue();
    }

}