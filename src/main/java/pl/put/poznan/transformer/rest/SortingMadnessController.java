package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.SortingMadness;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sort")
public class SortingMadnessController {

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    @ControllerAdvice
    public static class GlobalExceptionHandler {

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
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Map<String, List<Integer>> post(@RequestBody SortingMadness sortingMadness) {

        logger.info("POST");
        logger.info("Strategy: {}", sortingMadness.getStrategy());
        logger.info("Comparator: {}", sortingMadness.getComparator());
        logger.info("Criterion: {}", sortingMadness.getCriterion());
        logger.info("Objects: {}", sortingMadness.getObjects());
        return sortingMadness.sort();

    }
    @RequestMapping(method = RequestMethod.GET, value = "/algorithms", produces = "application/json")
    public ResponseEntity<?> getSortingAlgorithms() {
        logger.info("GET /sort/algorithms");
        List<String> algorithms = Arrays.asList(
                "MergeSort",
                "SelectSort",
                "InsertionSort"
        );
        return ResponseEntity.ok(Map.of("algorithms", algorithms));
    }

}


