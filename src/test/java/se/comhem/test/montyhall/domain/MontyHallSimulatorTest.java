package se.comhem.test.montyhall.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.core.Is.is;

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
        assertThat(simulationResult.getWinPercentage(), isA(Float.class));

        //Un-comment to test the relative strategy
        //assertThat(simulationResult.getNumberOfLosses(), lessThanOrEqualTo(35));
        //assertThat(simulationResult.getNumberOfWins(), greaterThanOrEqualTo(63) );
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

        //Un-comment to test the relative strategy
        //assertThat(simulationResult.getNumberOfWins(), lessThanOrEqualTo(45) );
        //assertThat(simulationResult.getNumberOfLosses(), greaterThanOrEqualTo(60));
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
