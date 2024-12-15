package pl.put.poznan.sortingmadness.logic;

public class SortingStrategyHeapSort implements SortingStrategy {



    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(SortedObject objects[],Comparator comparator, int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && comparator.compare(objects[l], objects[largest]) > 0)//arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && comparator.compare(objects[r],objects[largest]) > 0  && comparator.compare(objects[r],objects[l])>0)//arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            SortedObject swap = objects[i];
            objects[i] = objects[largest];
            objects[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(objects,comparator, n, largest);
        }
    }

    @Override
    public void sort(SortedObject[] objects, Comparator comparator) {

        int n = objects.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(objects,comparator, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            SortedObject temp = objects[0];
            objects[0] = objects[i];
            objects[i] = temp;

            // call max heapify on the reduced heap
            heapify(objects,comparator, i, 0);
        }

    }
}
