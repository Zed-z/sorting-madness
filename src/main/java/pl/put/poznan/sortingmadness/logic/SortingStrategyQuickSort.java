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
}
