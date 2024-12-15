package pl.put.poznan.sortingmadness.logic;

public class SortingStrategySelectSort implements SortingStrategy {
    public SortingStrategySelectSort() {
        super();
    }
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
