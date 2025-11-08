package com.numgame;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NumberGuessingApp extends Application {

    private NumberGuessingGame game;

    @Override
    public void start(Stage primaryStage) {
        game = new NumberGuessingGame(10);

        Label PromptLabel = new Label ("Guess a number between 0 - 100");
        TextField inputField = new TextField();
        Button guessButton = new Button("Submit Answer");
        Label feedbackLabel = new Label();
        Label attemptsLabel = new Label("You have " + game.getAttemptsLeft() + " Attempts left.");
        Button playAgainButton = new Button("Play again?");
        playAgainButton.setVisible(false);

        playAgainButton.setOnAction(e -> {
            game = new NumberGuessingGame(10);

            playAgainButton.setVisible(false);
            feedbackLabel.setText("");
            attemptsLabel.setText("You have " + game.getAttemptsLeft() + " Attempts left.");
            attemptsLabel.setVisible(true);
            guessButton.setDisable(false);
            inputField.setDisable(false);
        });

        guessButton.setOnAction(e -> {
            try {
                int guess = Integer.parseInt(inputField.getText());
                String feedback = game.checkGuess(guess);
                feedbackLabel.setText(feedback);
                attemptsLabel.setText("You have " + game.getAttemptsLeft() + " Attempts left.");

                if (game.isGameOver()) {
                    playAgainButton.setVisible(true);
                    guessButton.setDisable(true);
                    inputField.setDisable(true);
                    attemptsLabel.setVisible(false);
                }
            }
            catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
            inputField.clear();
        });

        inputField.setOnAction(e -> guessButton.fire());

        VBox root = new VBox(10, PromptLabel, inputField, guessButton, feedbackLabel, attemptsLabel, playAgainButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new javafx.geometry.Insets(20));
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

    }
}
