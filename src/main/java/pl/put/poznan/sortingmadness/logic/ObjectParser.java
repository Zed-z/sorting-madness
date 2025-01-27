package pl.put.poznan.sortingmadness.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectParser {
    /**
     * Converts objects from the REST API request into a list of SortedObjects
     * @return List of SortedObjects
     */
    public static List<SortedObject> generateObjectsToSort(List<Object> objects, String criterion, Logger logger) {
        // Get data types ----------------------------------------------------------------------------------------------
        boolean intUsed = false;
        boolean doubleUsed = false;
        boolean stringUsed = false;

        if (objects == null || objects.isEmpty()) {
            logger.error("No objects provided for sorting");
            throw new IllegalArgumentException("No objects provided for sorting");
        }

        logger.debug("Data value types:");
        for (Object object : objects) {
            if (object instanceof Map) {
                if (criterion == null) {
                    logger.error("No criterion provided for sorting");
                    throw new IllegalArgumentException("No criterion provided for sorting");
                }
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
}
