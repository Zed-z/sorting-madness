package pl.put.poznan.sortingmadness.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sortingmadness.logic.SortingMadness;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * A controller responsible for receiving requests and responding to them;
 * Calls the SortingMadness class to do most of the work
 */
@RestController
@RequestMapping("/sort")
public class SortingMadnessController {

    /**
     * A handle to the logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    /**
     * An exception handler to respond to invalid data being sent
     */
    @ControllerAdvice
    public static class GlobalExceptionHandler {

        /**
         * The entity to respond with the error to
         * @param ex The exception object
         * @return A REST response
         */
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", ex.getMessage(),
                            "details", "Check the data and try again."
                    ));
        }
    }

    /**
     * A POST request to sort objects remotely
     * @param sortingMadness The sorting controller
     * @return A REST response
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> post(@RequestBody SortingMadness sortingMadness) {

        logger.info("POST ------------------");
        logger.info("Strategy: {}", sortingMadness.getStrategy());
        logger.info("Comparator: {}", sortingMadness.getComparator());
        logger.info("Criterion: {}", sortingMadness.getCriterion());
        logger.info("Objects: {}", sortingMadness.getObjects());
        logger.info("Steps: {}", sortingMadness.getSteps());
        logger.info("-----------------------");

        Map<String, Object> response = sortingMadness.sort();
        logger.info("Response: {}", response);
        return response;
    }

    /**
     * A GET request to get a list of available sorting algorithms
     * @return A list of available sorting algorithms
     */
    @RequestMapping(method = RequestMethod.GET, value = "/algorithms", produces = "application/json")
    public ResponseEntity<?> getSortingAlgorithms() {
        logger.info("GET /sort/algorithms");
        List<String> algorithms = Arrays.asList(
                "BubbleSort",
                "HeapSort",
                "InsertionSort",
                "MergeSort",
                "QuickSort",
                "SelectSort"
        );

        ResponseEntity<Map<String, List<String>>> response = ResponseEntity.ok(Map.of("algorithms", algorithms));
        logger.info("Response: {}", response);
        return response;
    }

}


