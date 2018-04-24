package se.comhem.test.montyhall.domain;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MontyHallSimulator {

    static final int NUMBER_OF_DOORS_THREE = 3;
    private Random random = new Random();

    public SimulationResult simulate(GameStrategy gameStrategy, int numberOfSimulations) {
        SimulationResult simulationResult = new SimulationResult(gameStrategy, numberOfSimulations);
        MontyHallGame montyHallGame = new MontyHallGame(NUMBER_OF_DOORS_THREE);

        for (int i = 0; i < numberOfSimulations; i++) {
            simulateOneGame(montyHallGame, gameStrategy);
            reportResult(montyHallGame, simulationResult);
            montyHallGame.reset();
        }

        return simulationResult;
    }

    private void simulateOneGame(MontyHallGame montyHallGame, GameStrategy gameStrategy) {
        montyHallGame.randomizePrizeDoor();
        montyHallGame.randomizePlayerDoor();
        montyHallGame.aIPickLastDoor();
        makeGameStrategyDecision(gameStrategy, montyHallGame);
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