package pl.put.poznan.sortingmadness.logic;

/**
 * An implementation of the Bubble Sort algorithm
 */
public class SortingStrategyHeapSort implements SortingStrategy {


    /**
     * A function to turn the object array into a heap
     * @param objects An array of objects to sort
     * @param comparator The comparator to use when sorting
     * @param n The size of the array
     * @param i The index of the element to use as the local vertex, its children have to be smaller than it
     */
    void heapify(SortedObject objects[], Comparator comparator, int n, int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;


        if (l < n && comparator.compare(objects[l], objects[largest]) > 0)
            largest = l;

        if (r < n && comparator.compare(objects[r],objects[largest]) > 0  && comparator.compare(objects[r],objects[l])>0)
            largest = r;


        if (largest != i) {
            SortedObject swap = objects[i];
            objects[i] = objects[largest];
            objects[largest] = swap;


            heapify(objects,comparator, n, largest);
        }
    }

    /**
     * A function to turn the object array into a heap, with a step limit
     * @param objects An array of objects to sort
     * @param comparator The comparator to use when sorting
     * @param n The size of the array
     * @param i The index of the element to use as the local vertex, its children have to be smaller than it
     * @param steps The step limit
     */
    void heapify(SortedObject objects[], Comparator comparator, int n, int i, int steps)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;


        if (l < n && comparator.compare(objects[l], objects[largest]) > 0)
            largest = l;

        if (r < n && comparator.compare(objects[r],objects[largest]) > 0  && comparator.compare(objects[r],objects[l])>0)
            largest = r;


        if (largest != i) {
            SortedObject swap = objects[i];
            objects[i] = objects[largest];
            objects[largest] = swap;

            if(steps-- > 0){
                heapify(objects,comparator, n, largest, steps);
            }
        }
    }


    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {

        int n = objects.length;


        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(objects,comparator, n, i);


        for (int i = n - 1; i >= 0; i--) {

            SortedObject temp = objects[0];
            objects[0] = objects[i];
            objects[i] = temp;

           
            heapify(objects,comparator, i, 0);
        }

    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator, int steps) {

        int n = objects.length;


        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(objects,comparator, n, i);


        for (int i = n - 1; i >= 0; i--) {

            SortedObject temp = objects[0];
            objects[0] = objects[i];
            objects[i] = temp;


            heapify(objects,comparator, i, 0, steps);
        }

    }
}
