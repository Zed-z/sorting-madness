package pl.put.poznan.transformer.logic;
import java.util.Random;

public class ComparatorRandom<T extends SortedObject> implements Comparator<T>{

    @Override
    public boolean compare(T a, T b) {
        Random r = new Random();
        return r.nextBoolean();
    }

}
