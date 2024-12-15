package pl.put.poznan.transformer.logic;
import java.util.Random;

public class ComparatorRandom<T extends SortedObject> implements Comparator<T>{

    @Override
    public int compare(T a, T b) {
        Random r = new Random();
        return r.nextBoolean() ? 1 : 0;
    }

}
