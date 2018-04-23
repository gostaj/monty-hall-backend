package se.comhem.test.montyhall.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class MontyHallSimulatorTest {

    private static final int NUMBER_OF_SIMULATIONS = 100;

    @Test
    public void shouldHaveGreaterWinRateWithGameStrategySwitch() {
        //Given
        MontyHallSimulator montyHallSimulator = new MontyHallSimulator();
        //When
        SimulationResult simulationResult = montyHallSimulator.simulate(GameStrategy.SWITCH, NUMBER_OF_SIMULATIONS);

        System.out.println(simulationResult);
        //Then
        assertThat(simulationResult.getNumberOfSimulations(), is(NUMBER_OF_SIMULATIONS));
        assertThat(simulationResult.getGameStrategy(), is(GameStrategy.SWITCH));
        assertThat(simulationResult.getNumberOfWins(), greaterThanOrEqualTo(63) );
        assertThat(simulationResult.getNumberOfLosses(), lessThanOrEqualTo(35));
        assertThat(simulationResult.getWinPercentage(), isA(Float.class));
    }

    @Test
    public void shouldHaveLesserWinRateWithGameStrategyKeep() {
        //Given
        MontyHallSimulator montyHallSimulator = new MontyHallSimulator();
        //When
        SimulationResult simulationResult = montyHallSimulator.simulate(GameStrategy.KEEP, NUMBER_OF_SIMULATIONS);

        System.out.println(simulationResult);
        //Then
        assertThat(simulationResult.getNumberOfSimulations(), is(NUMBER_OF_SIMULATIONS));
        assertThat(simulationResult.getGameStrategy(), is(GameStrategy.KEEP));
        assertThat(simulationResult.getNumberOfWins(), lessThanOrEqualTo(45) );
        assertThat(simulationResult.getNumberOfLosses(), greaterThanOrEqualTo(60));
    }

    @Test
    public void shouldSimulateWithGameStrategyRandom() {
        //Given
        MontyHallSimulator montyHallSimulator = new MontyHallSimulator();
        //When
        SimulationResult simulationResult = montyHallSimulator.simulate(GameStrategy.RANDOM, NUMBER_OF_SIMULATIONS);

        System.out.println(simulationResult);
        //Then
        assertThat(simulationResult.getNumberOfSimulations(), is(NUMBER_OF_SIMULATIONS));
        assertThat(simulationResult.getGameStrategy(), is(GameStrategy.RANDOM));
    }
}
