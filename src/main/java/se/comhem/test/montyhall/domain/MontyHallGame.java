package se.comhem.test.montyhall.domain;

import java.util.List;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MontyHallGame {
    private final int NUMBER_OF_DOORS;
    private final IntPredicate removePlayerDoor = number -> number != this.playerDoor;
    private final IntPredicate removePriceDoor = number -> number != this.priceDoor;
    private final IntPredicate removeAIDoor = number -> number != this.aiDoor;
    private Random random;
    private int priceDoor;
    private int playerDoor;
    private int aiDoor;

    MontyHallGame(final Random random, final int number_of_doors) {
        this.random = random;
        this.NUMBER_OF_DOORS = number_of_doors;
    }

    int getPlayerDoor() {
        return this.playerDoor;
    }

    int getAIDoor() {
        return this.aiDoor;
    }

    int getPriceDoor() {
        return this.priceDoor;
    }

    void randomizePriceDoor() {
        this.priceDoor = getRandomDoor();
    }

    void randomizePlayerDoor() {
        this.playerDoor = getRandomDoor();
    }

    private int getRandomDoor() {
        return this.random.nextInt(NUMBER_OF_DOORS);
    }

    void aIPickLastDoor() {
        this.aiDoor = this.chooseLastDoor(this.NUMBER_OF_DOORS, removePlayerDoor, removePriceDoor);
    }

    void playerSwitchDoor() {
        this.playerDoor = chooseLastDoor(NUMBER_OF_DOORS, removePlayerDoor, removeAIDoor);
    }

    private int chooseLastDoor(final int number_of_doors, IntPredicate removeFirstDoorPredicate, IntPredicate removeSecondDoorPredicate) {
        IntStream intStream = IntStream.range(0, number_of_doors)
                .filter(removeFirstDoorPredicate)
                .filter(removeSecondDoorPredicate);
        List<Integer> remainingDoors = intStream.boxed().collect(Collectors.toList());
        return remainingDoors.size() == 1 ? remainingDoors.get(0) : remainingDoors.get(this.random.nextInt(remainingDoors.size()));
    }

    boolean hasPlayerWon() {
        return this.playerDoor == this.priceDoor;
    }

    void reset() {
        this.playerDoor = 0;
        this.aiDoor = 0;
        this.priceDoor = 0;
    }
}