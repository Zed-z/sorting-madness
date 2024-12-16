package pl.put.poznan.sortingmadness.logic;

/**
 * Defines an interface for sorting algorithms that can be chosen by the client
 */
interface SortingStrategy {

    /**
     * A function to sort provided objects using a defined comparator
     * @param objects An array of objects to sort
     * @param comparator The comparator to use when sorting
     */
    public void sort(SortedObject[] objects, Comparator comparator);

    /**
     * A function to sort provided objects using a defined comparator, with a limited number of steps
     * @param objects An array of objects to sort
     * @param comparator The comparator to use when sorting
     * @param steps The maximum amount of steps to take to limit the runtime of the algorithm
     */
    public void sort(SortedObject[] objects, Comparator comparator, int steps);

}
