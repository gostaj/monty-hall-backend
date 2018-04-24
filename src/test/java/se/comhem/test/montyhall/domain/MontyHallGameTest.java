package se.comhem.test.montyhall.domain;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class MontyHallGameTest {
    private static final int NUMBER_OF_DOORS_LEFT = 2;

    @Test
    public void shouldHaveZeroHasDefaults() {
        //Given
        MontyHallGame montyHallGame = new MontyHallGame(new Random(), MontyHallSimulator.NUMBER_OF_DOORS_THREE);
        //Then
        assertThat(montyHallGame.getPriceDoor(), is(0));
        assertThat(montyHallGame.getAIDoor(), is(0));
        assertThat(montyHallGame.getPlayerDoor(), is(0));
    }

    @Test
    public void shouldRandomlySetPriceBehindADoor() {
        //Given
        Random random = mock(Random.class);
        when(random.nextInt(eq(MontyHallSimulator.NUMBER_OF_DOORS_THREE)))
                .thenReturn(1);

        MontyHallGame montyHallGame = new MontyHallGame(random, MontyHallSimulator.NUMBER_OF_DOORS_THREE);
        //When
        montyHallGame.randomizePriceDoor();
        //Then
        assertThat(montyHallGame.getPriceDoor(), is(1));
        verify(random, times(1)).nextInt(MontyHallSimulator.NUMBER_OF_DOORS_THREE);
    }

    @Test
    public void shouldPickADoorAtRandom() {
        //Given
        Random random = mock(Random.class);
        when(random.nextInt(eq(MontyHallSimulator.NUMBER_OF_DOORS_THREE)))
                .thenReturn(1);
        MontyHallGame montyHallGame = new MontyHallGame(random, MontyHallSimulator.NUMBER_OF_DOORS_THREE);
        //When
        montyHallGame.randomizePlayerDoor();
        //Then
        assertThat(montyHallGame.getPlayerDoor(), is(1));
        verify(random, times(1)).nextInt(MontyHallSimulator.NUMBER_OF_DOORS_THREE);
    }

    @Test
    public void shouldRandomPickTheLastDoorWhenPriceAndPlayerIsSameDoor() {
        Random random = mock(Random.class);
        when(random.nextInt(eq(NUMBER_OF_DOORS_LEFT)))
                .thenReturn(1);
        MontyHallGame montyHallGame = new MontyHallGame(random, MontyHallSimulator.NUMBER_OF_DOORS_THREE);
        //When
        montyHallGame.aIPickLastDoor();
        //Then
        assertThat(montyHallGame.getAIDoor(), is(2));
        verify(random, times(1)).nextInt(NUMBER_OF_DOORS_LEFT);
    }

    @Test
    public void shouldPickTheLastDoorWhenPriceAndPlayerIsNotSameDoor() {
        Random random = mock(Random.class);
        when(random.nextInt(eq(MontyHallSimulator.NUMBER_OF_DOORS_THREE)))
                .thenReturn(0)
                .thenReturn(1);

        when(random.nextInt(eq(NUMBER_OF_DOORS_LEFT)))
                .thenReturn(0);

        MontyHallGame montyHallGame = new MontyHallGame(random, MontyHallSimulator.NUMBER_OF_DOORS_THREE);
        montyHallGame.randomizePriceDoor(); // 0
        montyHallGame.randomizePlayerDoor(); // 1
        //When
        montyHallGame.aIPickLastDoor();
        //Then
        assertThat(montyHallGame.getAIDoor(), is(2));
        verify(random, times(2)).nextInt(MontyHallSimulator.NUMBER_OF_DOORS_THREE);
    }

    @Test
    public void shouldBeAbleSwitchDoor() {
        //Given price 0 player 0
        Random random = mock(Random.class);
        when(random.nextInt(eq(NUMBER_OF_DOORS_LEFT)))
                .thenReturn(1)
                .thenReturn(2);

        MontyHallGame montyHallGame = new MontyHallGame(random, MontyHallSimulator.NUMBER_OF_DOORS_THREE);
        montyHallGame.aIPickLastDoor(); // 2
        assertThat(montyHallGame.getAIDoor(), is(2));
        //When
        montyHallGame.playerSwitchDoor();
        //Then
        assertThat(montyHallGame.getPlayerDoor(), is(1));
        verify(random, times(1)).nextInt(2);

    }

    @Test
    public void shouldTellIfPlayerHasNotChosenThePriceDoor() {
        //Given
        Random random = mock(Random.class);
        when(random.nextInt(eq(MontyHallSimulator.NUMBER_OF_DOORS_THREE)))
                .thenReturn(0)
                .thenReturn(1);

        MontyHallGame montyHallGame = new MontyHallGame(random, MontyHallSimulator.NUMBER_OF_DOORS_THREE);

        montyHallGame.randomizePriceDoor(); // 0
        montyHallGame.randomizePlayerDoor(); // 1
        montyHallGame.aIPickLastDoor(); // 2
        //When
        boolean hasWon = montyHallGame.hasPlayerWon();
        //Then
        assertThat(hasWon, is(false));
    }

    @Test
    public void shouldTellIfPlayerHasChosenThePriceDoor() {
        //Given player 0 price 0
        MontyHallGame montyHallGame = new MontyHallGame(new Random(), MontyHallSimulator.NUMBER_OF_DOORS_THREE);
        //When
        boolean hasWon = montyHallGame.hasPlayerWon();
        //Then
        assertThat(hasWon, is(true));
    }

    @Test
    public void shouldResetGameToDefaults() {
        //Given
        Random random = mock(Random.class);
        when(random.nextInt(eq(MontyHallSimulator.NUMBER_OF_DOORS_THREE)))
                .thenReturn(0)
                .thenReturn(1);

        MontyHallGame montyHallGame = new MontyHallGame(random, MontyHallSimulator.NUMBER_OF_DOORS_THREE);

        montyHallGame.randomizePriceDoor(); // 0
        montyHallGame.randomizePlayerDoor(); // 1
        montyHallGame.aIPickLastDoor(); // 2

        assertThat(montyHallGame.getPriceDoor(), is(0));
        assertThat(montyHallGame.getPlayerDoor(), is(1));
        assertThat(montyHallGame.getAIDoor(), is(2));
        // When
        montyHallGame.reset();
        //Then
        assertThat(montyHallGame.getAIDoor(), is(0));
        assertThat(montyHallGame.getPlayerDoor(), is(0));
        assertThat(montyHallGame.getPriceDoor(), is(0));
    }
}
