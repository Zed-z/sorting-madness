package pl.put.poznan.sortingmadness.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * The class responsible for preparing sorting objects, strategies and comparators;
 * Gets its data from the REST controller and returns the result of the selected parameters
 */
public class SortingMadness {
    /**
     * The name of the sorting algorithm to use
     */
    private List<String> algorithms;
    /**
     * The name of the comparator to use
     */
    private String comparator;
    /**
     * The criterion to use (if complex objects are passed)
     */
    private String criterion;
    /**
     * The list of provided objects / values
     */
    private List<Object> objects;
    /**
     * The limit of steps to take when sorting
     */
    private Integer steps;

    /**
     * The comparator to be used when sorting
     */
    private Comparator sortingComparator;
    /**
     * The array of objects to be used when sorting
     */
    SortedObject[] sortedObjectsArray;
    /**
     * The class of objects to be sorted
     */
    private Class sortedObjectClass;

    /**
     * A handle to the logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SortingMadness.class);

    /**
     * Does the sorting, as requested by the REST API
     * @return JSON response to the request
     */
    public Map<String, Object> sort() {
        sortInit();
        List<Map<String, Object>> results = new ArrayList<>();

        if (algorithms==null || algorithms.isEmpty()) {
            algorithms = new ArrayList<>();
            algorithms.add(StrategyRecommender.recommendStrategy(sortedObjectClass, objects));
            logger.debug("Recommended sorting strategy: {}", algorithms.get(0));
        }
        for (String algorithm : algorithms) {
            SortingStrategy sortingStrategy;
            switch (algorithm) {
                case "BubbleSort":
                    sortingStrategy = new SortingStrategyBubbleSort();
                    break;
                case "HeapSort":
                    sortingStrategy = new SortingStrategyHeapSort();
                    break;
                case "InsertionSort":
                    sortingStrategy = new SortingStrategyInsertionSort();
                    break;
                case "MergeSort":
                    sortingStrategy = new SortingStrategyMergeSort();
                    break;
                case "QuickSort":
                    sortingStrategy = new SortingStrategyQuickSort();
                    break;
                case "SelectSort":
                    sortingStrategy = new SortingStrategySelectSort();
                    break;
                default:
                    logger.error("Invalid sorting strategy: {}", algorithm);
                    throw new IllegalArgumentException("Invalid sorting strategy: " + algorithm);
            }

            logger.debug("Sorting strategy: " + sortingStrategy.getClass().getSimpleName());

            // copied so every sorting has its own array to work on
            SortedObject[] objectsToSort = Arrays.copyOf(sortedObjectsArray, sortedObjectsArray.length);

            long startTime = System.nanoTime();
            if (steps == null) {
                sortingStrategy.sort(objectsToSort, sortingComparator);
            } else {
                sortingStrategy.sort(objectsToSort, sortingComparator, steps);
            }
            long endTime = System.nanoTime();
            double timeElapsed = (endTime - startTime) / 1_000_000.0;

            Map<String, Object> singleResult = new HashMap<>();
            singleResult.put("algorithm", algorithm);
            singleResult.put("indexes", getIndices(objectsToSort));
            singleResult.put("time", timeElapsed);
            results.add(singleResult);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("results", results);

        return result;
    }


    /**
     * Generates a list of indices based on the sorted order of objects
     * @return A list of ordered object indices
     */
    public List<Integer> getIndices(SortedObject[] objectsToSort) {
        List<Integer> indices = new ArrayList<>();
        for (SortedObject sortedObject : objectsToSort) {
            indices.add(sortedObject.getIndex());
        }
        return indices;
    }

    /**
     * Initializes objects and the comparator used for sorting
     */
    public void sortInit() {
        List<SortedObject> sortedObjects = ObjectParser.generateObjectsToSort(objects, criterion, logger);
        this.sortedObjectsArray = sortedObjects.toArray(new SortedObject[0]);
        this.sortedObjectClass = sortedObjects.get(0).getClass();
        this.sortingComparator = generateComparator();

        logger.debug("Object array to sort: {}", Arrays.toString(sortedObjectsArray));
        logger.debug("Comparator: {}", sortingComparator.getClass().getSimpleName());
    }

    /**
     * Decodes a string name to a comparator object
     * @return The comparator decoded from the string
     */
    public Comparator generateComparator() {

        if (comparator==null) { throw new IllegalArgumentException("Please specify the comparator.");}
        switch (comparator) {
            case "Greater":
                return new ComparatorGreater();
            case "Lesser":
                return new ComparatorLesser();
            case "Longer":
                return new ComparatorLonger();
            case "Random":
                return new ComparatorRandom();
            case "Shorter":
                return new ComparatorShorter();
            default:
                logger.error("Unknown comparator: {}", comparator);
                throw new IllegalArgumentException("Unknown comparator.");
        }
    }






    /**
     * Getter for the algorithms
     * @return The algorithms
     */
    public List<String> getAlgorithms() {
        return algorithms;
    }

    /**
     * Getter for the comparator
     * @return The comparator
     */
    public String getComparator() {
        return comparator;
    }

    /**
     * Setter for the compataror
     * @param comparator The comparator to set
     */
    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    /**
     * Getter for the criterion
     * @return The criterion
     */
    public String getCriterion() {
        return criterion;
    }

    /**
     * Getter for the raw object list
     * @return The raw object list
     */
    public List<Object> getObjects() {
        return objects;
    }

    /**
     * Getter for the sorting steps amount
     * @return The sorting steps amount
     */
    public Integer getSteps() {
        return steps;
    }
}
