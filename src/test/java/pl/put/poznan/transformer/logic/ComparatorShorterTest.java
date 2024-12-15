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
    public void longerString(){
        assertTrue(c.compare(new SortedObjectString(0, "dluzszamalpa"), new SortedObjectString(1,"malpa")) == 1);
    }

    @Test
    public void shorterString(){
        assertTrue(c.compare(new SortedObjectString(0, "malpa"),
                new SortedObjectString(1,"dluzszamalpa")) == -1);
    }

    @Test
    public void equalString(){
        assertTrue(c.compare(new SortedObjectString(0, "malpa"),new SortedObjectString(1, "goryl")) == 0);
    }

    @Test
    public void longerInt(){
        assertTrue(c.compare(new SortedObjectInt(0,8008135), new SortedObjectInt(1, 68)) == 1);
    }

    @Test
    public void shorterInt(){
        assertTrue(c.compare(new SortedObjectInt(0,419), new SortedObjectInt(1, 8008135)) == -1);
    }

    @Test
    public void equalInt(){
        assertTrue(c.compare(new SortedObjectInt(0,419), new SortedObjectInt(1, 419)) == 0);
    }
}
