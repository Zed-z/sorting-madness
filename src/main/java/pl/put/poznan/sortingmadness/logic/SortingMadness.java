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
        List<SortedObject> sortedObjects = generateObjectsToSort();
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
     * Converts objects from the REST API request into a list of SortedObjects
     * @return List of SortedObjects
     */
    public List<SortedObject> generateObjectsToSort() {
        // Get data types ----------------------------------------------------------------------------------------------
        boolean intUsed = false;
        boolean doubleUsed = false;
        boolean stringUsed = false;

        logger.debug("Data value types:");
        for (Object object : objects) {
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                Object value = map.get(criterion);

                logger.debug(" - {}", value.getClass().getSimpleName());

                if (value.getClass().equals(Integer.class)) {
                    intUsed = true;
                } else if (value.getClass().equals(Float.class)) {
                    doubleUsed = true;
                } else if (value.getClass().equals(Double.class)) {
                    doubleUsed = true;
                } else if (value.getClass().equals(String.class)) {
                    stringUsed = true;
                }

            } else {

                logger.debug(" - {}", object.getClass().getSimpleName());

                if (object.getClass().equals(Integer.class)) {
                    intUsed = true;
                } else if (object.getClass().equals(Float.class)) {
                    doubleUsed = true;
                } else if (object.getClass().equals(Double.class)) {
                    doubleUsed = true;
                } else if (object.getClass().equals(String.class)) {
                    stringUsed = true;
                }

            }
        }

        Class<?> classToUse = null;
        if (stringUsed) {
            classToUse = String.class;
        } else if (doubleUsed) {
            classToUse = Double.class;
        } else if (intUsed) {
            classToUse = Integer.class;
        }

        if (stringUsed && (intUsed || doubleUsed)) {
            logger.error("Found mixed data types (numeric and string)!");
            throw new IllegalArgumentException("Found mixed data types (numeric and string)!");
        }

        if (classToUse == null) {
            logger.error("Found incompatible data types!");
            throw new IllegalArgumentException("Found incompatible data types!");
        }

        logger.debug("Using type: {}", classToUse.getSimpleName());

        // Sort values -------------------------------------------------------------------------------------------------
        List<SortedObject> sortedObjects = new ArrayList<>();
        int index = 0;

        logger.debug("Objects:");
        for (Object object : objects) {

            Object value;
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                value = map.get(criterion);
            } else {
                value = object;
            }

            logger.debug(" - {}: {}", object, value);

            if (classToUse == Integer.class) {
                SortedObjectInt sortedObjectInt = new SortedObjectInt(index, (Integer) value);
                sortedObjects.add(sortedObjectInt);
            } else if (classToUse == Double.class) {
                if (value instanceof Integer) {
                    SortedObjectDouble sortedObjectDouble = new SortedObjectDouble(index, ((Integer) value).doubleValue());
                    sortedObjects.add(sortedObjectDouble);
                } else if (value instanceof Float || value instanceof Double) {
                    SortedObjectDouble sortedObjectDouble = new SortedObjectDouble(index, (Double) value);
                    sortedObjects.add(sortedObjectDouble);
                }
            } else if (classToUse == String.class) {
                SortedObjectString sortedObjectStr = new SortedObjectString(index, (String) value);
                sortedObjects.add(sortedObjectStr);
            }

            index++;
        }

        return sortedObjects;
    }

    /**
     * A function to recommend the sorting strategy to use base on data type and amount
     * @return The name of the sort algorithm to use
     */
    public String recommendStrategy() {
        if (sortedObjectClass == SortedObjectString.class) {
            return "MergeSort";
        }

        if (objects.size() <= 10) {
            return "InsertionSort";
        } else if (objects.size() <= 100) {
            return "QuickSort";
        } else {
            return "HeapSort";
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
