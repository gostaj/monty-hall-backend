package se.comhem.test.montyhall.domain;

import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MontyHallGame {

    private final int NUMBER_OF_DOORS;

    private int prizeDoor;
    private int playerDoor;
    private int aiDoor;

    private final IntPredicate isNotPlayerDoor = number -> number != playerDoor;
    private final IntPredicate isNotPrizeDoor = number -> number != prizeDoor;
    private final IntPredicate isNotAIDoor = number -> number != aiDoor;

    private Random random;

    MontyHallGame(final int numberOfDoors) {
        random = new Random();
        NUMBER_OF_DOORS = numberOfDoors;
    }

    MontyHallGame(final Random random, final int numberOfDoors) {
        this.random = random;
        NUMBER_OF_DOORS = numberOfDoors;
    }

    int getPlayerDoor() {
        return this.playerDoor;
    }

    int getAIDoor() {
        return this.aiDoor;
    }

    int getPrizeDoor() {
        return this.prizeDoor;
    }

    void randomizePrizeDoor() {
        this.prizeDoor = getRandomDoor();
    }

    void randomizePlayerDoor() {
        this.playerDoor = getRandomDoor();
    }

    private int getRandomDoor() {
        return this.random.nextInt(NUMBER_OF_DOORS);
    }

    void aIPickLastDoor() {
        this.aiDoor = this.chooseAnyRemainingDoor(this.NUMBER_OF_DOORS, isNotPlayerDoor.and(isNotPrizeDoor));
    }

    void playerSwitchDoor() {
        this.playerDoor = chooseAnyRemainingDoor(NUMBER_OF_DOORS, isNotPlayerDoor.and(isNotAIDoor));
    }

    private int chooseAnyRemainingDoor(final int number_of_doors, IntPredicate notSelectedDoors) {
        IntStream intStream = IntStream.range(0, number_of_doors)
                .filter(notSelectedDoors);
        List<Integer> remainingDoors = intStream.boxed().collect(Collectors.toList());
        return remainingDoors.size() == 1 ?
                remainingDoors.get(0) : getRandomElementFromList(remainingDoors);
    }

    private <T> T getRandomElementFromList(List<T> list) {
        return list.get(this.random.nextInt(list.size()));
    }

    boolean hasPlayerWon() {
        return this.playerDoor == this.prizeDoor;
    }

    void reset() {
        this.playerDoor = 0;
        this.aiDoor = 0;
        this.prizeDoor = 0;
    }
}