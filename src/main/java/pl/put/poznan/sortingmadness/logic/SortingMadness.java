package pl.put.poznan.sortingmadness.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The class responsible for preparing sorting objects, strategies and comparators;
 * Gets its data from the REST controller and returns the result of the selected parameters
 */
public class SortingMadness {
    private String strategy;
    private String comparator;
    private String criterion;
    private List<Object> objects;
    private Comparator sortingComparator;
    SortedObject[] sortedObjectsArray;
    List<Integer> indices;

    private static final Logger logger = LoggerFactory.getLogger(SortingMadness.class);

    public Map<String, List<Integer>> sort() {
    sortInit();

    SortingStrategy sortingStrategy = null;
        switch (strategy) {
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
                logger.error("Invalid sorting strategy!");
                throw new IllegalArgumentException("Invalid sorting strategy!");
        }

        sortingStrategy.sort(sortedObjectsArray, sortingComparator);
        addIndices();

        Map<String, List<Integer>> result = new HashMap<>();
        result.put("indexes", indices);

        return result;
    }

    public void addIndices() {
        indices = new ArrayList<>();
        for (SortedObject sortedObject : sortedObjectsArray) {
            Integer index = sortedObject.getIndex();
            indices.add(index);
        }
    }

    public void sortInit() {
        List<SortedObject> sortedObjects = generateObjectsToSort();
        this.sortedObjectsArray = sortedObjects.toArray(new SortedObject[0]);
        this.sortingComparator = generateComparator();
    }

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


    public List<SortedObject> generateObjectsToSort() {

        // Get data types ----------------------------------------------------------------------------------------------
        boolean intUsed = false;
        boolean doubleUsed = false;
        boolean stringUsed = false;

        logger.debug("Data value types on criterion: {}", criterion);
        for (Object object : objects) {
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                Object value = map.get(criterion);

                logger.debug("{}", value.getClass().getSimpleName());

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

                logger.debug("{}", object.getClass().getSimpleName());

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

        // Sort values -------------------------------------------------------------------------------------------------
        List<SortedObject> sortedObjects = new ArrayList<>();
        int index = 0;

        for (Object object : objects) {
            logger.debug("Criterion: {}", criterion);
            logger.debug("Sorting object: {}", object);

            Object value;
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                value = map.get(criterion);
            } else {
                value = object;
            }

            logger.debug("Value: {}", value);

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

    public String getStrategy() {
        return strategy;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public String getCriterion() {
        return criterion;
    }

    public List<Object> getObjects() {
        return objects;
    }
}
