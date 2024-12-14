package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.SortedObject;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sort")
public class SortingController {

    private static final Logger logger = LoggerFactory.getLogger(SortingController.class);

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
    public SortedObject[] post(@RequestBody Sorter sorter) {

        logger.info("POST");
        logger.info("Strategy: {}", sorter.getStrategy());
        logger.info("Comparator: {}", sorter.getComparator());
        logger.info("Criterion: {}", sorter.getCriterion());
        logger.info("Objects: {}", sorter.getObjects());
        SortedObject[] output = sorter.sort();
        return output;

    }
    @RequestMapping(method = RequestMethod.GET, value = "/algorithms", produces = "application/json")
    public ResponseEntity<?> getSortingAlgorithms() {
        logger.info("GET /sort/algorithms");
        List<String> algorithms = Arrays.asList(
                "MergeSort",
                "SelectSort"
        );
        return ResponseEntity.ok(Map.of("algorithms", algorithms));
    }

}


