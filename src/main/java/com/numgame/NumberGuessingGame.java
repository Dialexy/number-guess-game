package com.numgame;

import java.util.Random;

public class NumberGuessingGame {
    private final int targetNumber;
    private final int attempts;
    private int attemptsLeft;
    private boolean gameOver;

    public int getTargetNumber() {
        return targetNumber;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public NumberGuessingGame(int attempts) {
        Random rand = new Random();
        this.targetNumber = rand.nextInt(100) + 1;
        this.attempts = attempts;
        this.attemptsLeft = attempts;
    }

    public String checkGuess(int guess) {
        if (guess < 1 || guess > 100) {
            return "Your guess must be between 1 - 100";
        }
        if (guess == targetNumber) {
            gameOver = true;
            return "Your guess is correct!";
        }
        attemptsLeft--;
        if (attemptsLeft == 0) {
            gameOver = true;
        }
        if (guess < targetNumber) {
            return "Your guess is too low!";
        }
        return "Your guess is too high!";
    }
}
