package se.comhem.test.montyhall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.comhem.test.montyhall.domain.GameStrategy;
import se.comhem.test.montyhall.domain.MontyHallSimulator;
import se.comhem.test.montyhall.domain.SimulationResult;

import javax.servlet.http.HttpServletRequest;

import static java.text.MessageFormat.format;

@RestController
@EnableAutoConfiguration
class MontyHallController {

    private static final int MAX_ITERATIONS = 1000000;

    @Autowired
    private MontyHallSimulator montyHallSimulator;

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody String
    handleBadRequest(HttpServletRequest req, Exception ex) {
        return ex.getMessage();
    }

    @RequestMapping("/simulate")
    @ResponseBody
    public SimulationResult simulate(@RequestParam(value="iterations", defaultValue="100") Integer iterations,
                                     @RequestParam("strategy") GameStrategy strategy)  {
        if(iterations < 0 || iterations > MAX_ITERATIONS){
            throw new IllegalArgumentException(format(
                    "Request parameter iterations must be positive number or not greater than {0}, was {1}",
                    iterations, MAX_ITERATIONS));
        }
        return montyHallSimulator.simulate(strategy, iterations);
    }
}
