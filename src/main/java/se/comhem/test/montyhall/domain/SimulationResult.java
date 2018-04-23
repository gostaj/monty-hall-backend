package se.comhem.test.montyhall.domain;

import static java.text.MessageFormat.format;

public class SimulationResult {

    private int numberOfLosses;
    private int numberOfWins;
    private GameStrategy gameStrategy;
    private int numberOfSimulations;

    public SimulationResult(GameStrategy gameStrategy, int numberOfSimulations) {
        this.gameStrategy = gameStrategy;
        this.numberOfSimulations = numberOfSimulations;
    }

    public int getNumberOfLosses() {
        return numberOfLosses;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public GameStrategy getGameStrategy() {
        return gameStrategy;
    }

    public int getNumberOfSimulations() {
        return numberOfSimulations;
    }

    public void incrementWins() {
        this.numberOfWins++;
    }

    public void incrementLosses() {
        this.numberOfLosses++;
    }

    public float getWinPercentage() {
        return calculateWinPercentage();
    }

    public String toString() {
        return format("Strategy {0} Simulations: {1} wins: {2} losses: {3} Percentage of wins: {4}", gameStrategy, numberOfSimulations, numberOfWins, numberOfLosses, calculateWinPercentage());
    }

    private float calculateWinPercentage() {
        return (numberOfWins * 100.0f) / numberOfSimulations;
    }
}
