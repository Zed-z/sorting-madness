package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

public class ComparatorLongerOrEqualTest {
    public static Comparator c;
    @BeforeAll
    public static void setUp(){
        c = new ComparatorLongerOrEqual();
    }
    @Test
    public void shorterstring(){
        assertFalse(c.compare(new SortedObjectString(0, "malpa"), new SortedObjectString(1,"dluzszamalpa")));
    }
    @Test
    public void longerstring(){
        assertTrue(c.compare(new SortedObjectString(0, "dluzszamalpa"), new SortedObjectString(1,"malpa")));
    }
    @Test
    public void equalstring(){
        assertTrue(c.compare(new SortedObjectString(0, "malpa"),new SortedObjectString(1, "goryl")));
    }
    @Test
    public void longerint(){
        assertTrue(c.compare(new SortedObjectInt(0,8008135), new SortedObjectInt(1, 68)));
    }
    @Test
    public void shorterint(){
        assertFalse(c.compare(new SortedObjectInt(0,419), new SortedObjectInt(1, 8008135)));
    }

}
