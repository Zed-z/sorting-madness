package pl.put.poznan.sortingmadness.logic;
import java.util.Random;

/**
 * A comparator that returns a random results, has possibly no practical use
 * @param <T> The type of SortedObject to compare
 */
public class ComparatorRandom<T extends SortedObject> implements Comparator<T>{

    @Override
    public int compare(T a, T b) {
        Random r = new Random();
        return r.nextBoolean() ? 1 : 0;
    }

}
