package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * Test suite for testing the ComparatorLonger
 */
public class ComparatorLongerTest {
    public static Comparator c;
    /**
     * setting up
     */
    @BeforeAll
    public static void setUp(){
        c = new ComparatorLonger();
    }
    /**
     * longer string first
     */
    @Test
    public void longerString(){
        SortedObjectString longermock = mock(SortedObjectString.class);
        when(longermock.getValue()).thenReturn("dluszamalpa");

        SortedObjectString shortermock = mock(SortedObjectString.class);
        when(shortermock.getValue()).thenReturn("malpa");

        assertTrue(c.compare(longermock, shortermock) == -1);
        verify(longermock, times(1)).getValue();
        verify(shortermock, times(1)).getValue();
    }
    /**
     * shorter string first
     */
    @Test
    public void shorterString(){

        SortedObjectString longermock = mock(SortedObjectString.class);
        when(longermock.getValue()).thenReturn("dluszamalpa");

        SortedObjectString shortermock = mock(SortedObjectString.class);
        when(shortermock.getValue()).thenReturn("malpa");
        assertTrue(c.compare(shortermock, longermock) == 1);
        verify(longermock, times(1)).getValue();
        verify(shortermock, times(1)).getValue();

    }
    /**
     * both of same length
     */
    @Test
    public void equalString(){

        SortedObjectString mock1 = mock(SortedObjectString.class);
        when(mock1.getValue()).thenReturn("jaknizej");

        SortedObjectString mock2 = mock(SortedObjectString.class);
        when(mock2.getValue()).thenReturn("jakwyzej");
        assertTrue(c.compare(mock1,mock2) == 0);
        verify(mock1, times(1)).getValue();
        verify(mock2, times(1)).getValue();
    }
    /**
     * longer Integer first
     */
    @Test
    public void longerInt(){
        SortedObjectInt longermock = mock(SortedObjectInt.class);
        when(longermock.getValue()).thenReturn(12345);
        SortedObjectInt shortermock = mock(SortedObjectInt.class);
        when(shortermock.getValue()).thenReturn(123);
        assertTrue(c.compare(longermock, shortermock) == -1);
        verify(longermock, times(1)).getValue();
        verify(shortermock, times(1)).getValue();
    }
    /**
     * shorter Integer first
     */
    @Test
    public void shorterInt(){

        SortedObjectInt longermock = mock(SortedObjectInt.class);
        when(longermock.getValue()).thenReturn(12345);
        SortedObjectInt shortermock = mock(SortedObjectInt.class);
        when(shortermock.getValue()).thenReturn(123);
        assertTrue(c.compare( shortermock, longermock) == 1);
        verify(longermock, times(1)).getValue();
        verify(shortermock, times(1)).getValue();
    }
    /**
     * both of same length
     */
    @Test
    public void equalInt(){
        SortedObjectInt mock1 = mock(SortedObjectInt.class);
        when(mock1.getValue()).thenReturn(12345);
        SortedObjectInt mock2 = mock(SortedObjectInt.class);
        when(mock2.getValue()).thenReturn(54321);
        assertTrue(c.compare(mock1, mock2) == 0);
        verify(mock1, times(1)).getValue();
        verify(mock2, times(1)).getValue();
    }


}
