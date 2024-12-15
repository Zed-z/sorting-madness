package pl.put.poznan.sortingmadness.logic;

/**
 * Defines an interface for sorting algorithms that can be chosen by the client
 */
interface SortingStrategy {

    /**
     * A function to sort provided objects using a defined comparator
     * @param objects An array of objects to sort
     * @param comparator A comparator to use when sorting
     */
    public void sort(SortedObject[] objects, Comparator comparator);

}
