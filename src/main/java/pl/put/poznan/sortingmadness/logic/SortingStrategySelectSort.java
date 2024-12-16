package pl.put.poznan.sortingmadness.logic;

/**
 * An implementation of the Select Sort algorithm
 */
public class SortingStrategySelectSort implements SortingStrategy {

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {
        for(int i = 0; i < objects.length; i++){
            int index = find_index(objects, comparator, i);
            if(index != i){
                SortedObject temp = objects[i];
                objects[i] = objects[index];
                objects[index] = temp;
            }
        }

    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator, int steps) {
        for(int i = 0; i < objects.length; i++){
            int index = find_index(objects, comparator, i);
            if(index != i){
                SortedObject temp = objects[i];
                objects[i] = objects[index];
                objects[index] = temp;
            }
        }

    }

    /**
     * Finds an extremum in the array
     * @param objects An array of objects to sort
     * @param comparator The comparator to use when sorting
     * @param start The index to start the search at
     * @return The found index of the extremum
     */
    public int find_index(SortedObject[] objects, Comparator comparator, int start){
        int index = start;
        for(int j = start; j < objects.length; j++){
            if(comparator.compare(objects[index], objects[j]) > 0){
                index = j;
            }
        }
        return index;
    }
}
