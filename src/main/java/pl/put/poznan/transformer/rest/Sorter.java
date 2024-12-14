package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Sorter {
    private String strategy;
    private String comparator;
    private String criterion;
    private List<Object> objects;
    private Comparator sortingComparator;
    SortedObject[] sortedObjectsArray;
    List<Integer> indices;

    private static final Logger logger = LoggerFactory.getLogger(Sorter.class);

    public Map<String, List<Integer>> sort() {
    sortInit();

        switch (strategy) {
            case "MergeSort":
                SortingStrategyMergeSort mergeSort = new SortingStrategyMergeSort();
                mergeSort.sort(sortedObjectsArray, sortingComparator);
                break;
            case "SelectSort":
                SortingStrategySelectSort selectSort = new SortingStrategySelectSort();
                selectSort.sort(sortedObjectsArray, sortingComparator);
                break;
            case "InsertionSort":
                SortingStrategyInsertionSort insertionSort = new SortingStrategyInsertionSort();
                insertionSort.sort(sortedObjectsArray, sortingComparator);
                break;
            default:
                logger.info("No such strategy.");
                throw new IllegalArgumentException("No such strategy");
        }
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
            case "GreaterOrEqual":
                return new ComparatorGreaterOrEqual();
            case "Greater":
                return new ComparatorGreater();
            case "Lesser":
                return new ComparatorLesser();
            case "LesserOrEqual":
                return new ComparatorLesserOrEqual();
            case "Longer":
                return new ComparatorLonger();
            case "LongerOrEqual":
                return new ComparatorLongerOrEqual();
            case "Random":
                return new ComparatorRandom();
            case "Shorter":
                return new ComparatorShorter();
            case "ShorterOrEqual":
                return new ComparatorShorterOrEqual();
            default:
                logger.warn("Unknown comparator: {}", comparator);
                throw new IllegalArgumentException("Unknown comparator.");
        }
    }


    public List<SortedObject> generateObjectsToSort() {
        List<SortedObject> sortedObjects = new java.util.ArrayList<>();
        int index = 0;
        for (Object object : objects) {
            logger.info("Criterion: {}", criterion);
            logger.info("Sorting object: {}", object);
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                Object value = map.get(criterion);
                if (value instanceof Integer) {
                    logger.info("Value is a number: {}", value);
                    SortedObjectInt sortedObjectInt = new SortedObjectInt(index, (Integer) value);
                    sortedObjects.add(sortedObjectInt);
                } else if (value instanceof String) {
                    logger.info("Value is a string: {}", value);
                    SortedObjectString sortedObjectStr = new SortedObjectString(index, (String) value);
                    sortedObjects.add(sortedObjectStr);
                } else {
                    logger.warn("Value is not a number or string: {}", value);
                    throw new IllegalArgumentException("Invalid data types (integer or string not found ).");
                }

                // Error check
                Boolean stringCheck = false;
                Boolean numberCheck = false;

                for (SortedObject sortedObjectString : sortedObjects) {

                    if (sortedObjectString instanceof SortedObjectString) {
                        stringCheck = true;
                    }
                    if (sortedObjectString instanceof SortedObjectInt) {
                        numberCheck = true;
                    }
                    if (stringCheck && numberCheck) {
                        logger.warn("Invalid data types (found string and number)");
                        throw new IllegalArgumentException("Invalid data types (found string and number).");
                    }
                }

            } else {
                logger.warn("Object is not a Map: {}", object);
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
