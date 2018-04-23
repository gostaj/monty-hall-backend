package se.comhem.test.montyhall.domain;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
public class MontyHallSimulator {
    static final int NUMBER_OF_DOORS_THREE = 3;
    private Random random = new Random();

    public SimulationResult simulate(GameStrategy gameStrategy, int numberOfSimulations) {
        SimulationResult simulationResult = new SimulationResult(gameStrategy, numberOfSimulations);
        MontyHallGame montyHallGame = new MontyHallGame(new Random(), NUMBER_OF_DOORS_THREE);

        IntStream.range(0, numberOfSimulations).forEach(i -> {
            montyHallGame.randomizePriceDoor();
            montyHallGame.randomizePlayerDoor();
            montyHallGame.aIPickLastDoor();
            makeGameStrategyDecision(gameStrategy, montyHallGame);
            reportResult(montyHallGame, simulationResult);
            montyHallGame.reset();
        });

        return simulationResult;
    }

    private void makeGameStrategyDecision(GameStrategy gameStrategy, MontyHallGame montyHallGame) {
        switch (gameStrategy) {
            case KEEP:
                break;
            case SWITCH:
                montyHallGame.playerSwitchDoor();
                break;
            case RANDOM:
                if (randomBoolean()) {
                    montyHallGame.playerSwitchDoor();
                }
                break;
            default: //KEEP
                break;
        }
    }

    private void reportResult(MontyHallGame montyHallGame, SimulationResult simulationResult) {
        if (montyHallGame.hasPlayerWon()) {
            simulationResult.incrementWins();
        } else {
            simulationResult.incrementLosses();
        }
    }

    private boolean randomBoolean() {
        return this.random.nextBoolean();
    }
}