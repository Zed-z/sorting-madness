package pl.put.poznan.sortingmadness.logic;

/**
 * An implementation of the Quick Sort algorithm
 */
public class SortingStrategyQuickSort implements SortingStrategy {

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {
        int q = objects.length -1;
        int p = 0;

        InternalSort(objects,comparator,p,q);
    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator, int steps) {
        int q = objects.length -1;
        int p = 0;

        InternalSort(objects,comparator,p,q, steps);
    }

    /**
     * A recursive function to perform the Quick Sort algorithm
     * @param objects An array of objects to sort
     * @param comparator The comparator to use when sorting
     * @param left The left bound of the array
     * @param right The right bound of the array
     */
    private void InternalSort(SortedObject[] objects, Comparator comparator, int left, int right) {

        int i  = left;
        int j = right;
        SortedObject pivot = objects[((i + j) >> 1)];

        while(i <= j)
        {
            while (comparator.compare(objects[i],pivot) < 0) {
                if( i  < right)
                    i++;
                else
                    break;
            }
            while (comparator.compare(objects[j],pivot) > 0){
                if ( j > left)
                    j--;
                else
                    break;
            }

            if( i <= j  && i <=right && j >= left)
            {
                SortedObject temp = objects[i];
                objects[i] = objects[j];
                objects[j] = temp;

                i ++;
                j--;
            }



        }
        if( left < j ) InternalSort(objects,comparator,left, j);
        if( right > i ) InternalSort(objects,comparator,i,right);

    }

    /**
     * A recursive function to perform the Quick Sort algorithm, with a step limit
     * @param objects An array of objects to sort
     * @param comparator The comparator to use when sorting
     * @param left The left bound of the array
     * @param right The right bound of the array
     * @param steps The step limit
     */
    private void InternalSort(SortedObject[] objects, Comparator comparator, int left, int right, int steps) {

        int i  = left;
        int j = right;
        SortedObject pivot = objects[((i + j) >> 1)];

        while(i <= j)
        {
            while (comparator.compare(objects[i],pivot) < 0) {
                if( i  < right)
                    i++;
                else
                    break;
            }
            while (comparator.compare(objects[j],pivot) > 0){
                if ( j > left)
                    j--;
                else
                    break;
            }

            if( i <= j  && i <=right && j >= left)
            {
                SortedObject temp = objects[i];
                objects[i] = objects[j];
                objects[j] = temp;

                i ++;
                j--;
            }



        }
        if(steps-- <= 0) return;
        if( left < j ) InternalSort(objects,comparator,left, j);
        if( right > i ) InternalSort(objects,comparator,i,right);

    }
}
