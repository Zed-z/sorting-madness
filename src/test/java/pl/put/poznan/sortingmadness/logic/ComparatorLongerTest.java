package pl.put.poznan.sortingmadness.logic;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
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
        assertTrue(c.compare(new SortedObjectString(0, "dluzszamalpa"), new SortedObjectString(1,"malpa")) == -1);
    }
    /**
     * shorter string first
     */
    @Test
    public void shorterString(){
        assertTrue(c.compare(new SortedObjectString(0, "malpa"),
                new SortedObjectString(1,"dluzszamalpa")) == 1);
    }
    /**
     * both of same length
     */
    @Test
    public void equalString(){
        assertTrue(c.compare(new SortedObjectString(0, "malpa"),new SortedObjectString(1, "goryl")) == 0);
    }
    /**
     * longer Integer first
     */
    @Test
    public void longerInt(){
        assertTrue(c.compare(new SortedObjectInt(0,8008135), new SortedObjectInt(1, 68)) == -1);
    }
    /**
     * shorter Integer first
     */
    @Test
    public void shorterInt(){
        assertTrue(c.compare(new SortedObjectInt(0,419), new SortedObjectInt(1, 8008135)) == 1);
    }
    /**
     * both of same length
     */
    @Test
    public void equalInt(){
        assertTrue(c.compare(new SortedObjectInt(0,419), new SortedObjectInt(1, 421)) == 0);
    }

}
