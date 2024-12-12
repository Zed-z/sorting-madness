package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparatorShorterTest {
    public static Comparator c;
    @BeforeAll
    public static void setUp(){
        c = new ComparatorShorter();
    }
    @Test
    public void shorterstring(){
        assertTrue(c.compare(new SortedObjectString(0, "malpa"),
                new SortedObjectString(1,"dluzszamalpa")));
    }
    @Test
    public void equalstring(){
        assertFalse(c.compare(new SortedObjectString(0, "malpa"),new SortedObjectString(1, "goryl")));
    }
    @Test
    public void longerstring(){
        assertFalse(c.compare(new SortedObjectString(0, "dluzszamalpa"), new SortedObjectString(1,"malpa")));
    }
    @Test
    public void longerint(){
        assertFalse(c.compare(new SortedObjectInt(0,8008135), new SortedObjectInt(1, 68)));
    }
    @Test
    public void shorterint(){
        assertTrue(c.compare(new SortedObjectInt(0,419), new SortedObjectInt(1, 8008135)));
    }
}
