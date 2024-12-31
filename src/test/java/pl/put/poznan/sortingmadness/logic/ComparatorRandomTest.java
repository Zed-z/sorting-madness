package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Test suite for testing ComparatorRandom
 */
class ComparatorRandomTest {

    ComparatorRandom comparatorRandom;

    @BeforeEach
    void setUp() {
        comparatorRandom = new ComparatorRandom();
    }

    /**
     * Should return whatever, but only -1, 0 or 1
     * getValue should not run at all, what's the point?
     */
    @Test
    public void random() {
        SortedObjectInt mock1 = mock(SortedObjectInt.class);
        when(mock1.getValue()).thenReturn(5);
        SortedObjectInt mock2 = mock(SortedObjectInt.class);
        when(mock2.getValue()).thenReturn(5);

        List<Integer> allowedValues = new ArrayList<>();
        allowedValues.add(-1);
        allowedValues.add(0);
        allowedValues.add(1);

        int result = comparatorRandom.compare(mock1, mock2);
        assertTrue(allowedValues.contains(result));
        verify(mock1, times(0)).getValue();
        verify(mock2, times(0)).getValue();
    }

}