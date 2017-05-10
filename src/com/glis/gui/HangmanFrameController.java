package com.glis.gui;

import com.glis.domain.HangmanGame;
import com.glis.domain.Word;
import com.glis.domain.provider.WordProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author Glis
 */
@SuppressWarnings({"unused"})
public class HangmanFrameController extends HBox {
    private final static Image[] IMAGES = new Image[]{
            new Image("/image/0.png"),
            new Image("/image/1.png"),
            new Image("/image/2.png"),
            new Image("/image/3.png"),
            new Image("/image/4.png"),
            new Image("/image/5.png"),
            new Image("/image/6.png"),
            new Image("/image/7.png"),
            new Image("/image/8.png"),
            new Image("/image/9.png"),
            new Image("/image/10.png"),
            new Image("/image/11.png"),
            new Image("/image/12.png"),
    };

    private final WordProvider wordProvider;

    @FXML
    private ImageView hangmanPicture;

    @FXML
    private Text textError;

    @FXML
    private Text triedLetters;

    @FXML
    private Text word;

    @FXML
    private TextField letterToGuess;

    private HangmanGame hangmanGame;

    public HangmanFrameController(final WordProvider wordProvider) {
        this.wordProvider = wordProvider;
        FXMLLoader load = new FXMLLoader();
        load.setLocation(getClass().getResource("/fxml/Hangman.fxml"));
        load.setRoot(this);
        load.setController(this);
        try {
            load.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        letterToGuess.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Objects.equals(oldValue, newValue)) {
                return;
            }
            if(newValue == null)
                return;
            if(newValue.length() > 1) {
                letterToGuess.setText(newValue.substring(newValue.length() - 1, newValue.length()));
            }
        });
    }

    private void startNewGame() {
        textError.setText("");
        List<Word> possibleWords = new ArrayList<>(wordProvider.getAllWords());
        this.hangmanGame = new HangmanGame(possibleWords.get((int)(new Random().nextDouble() * possibleWords.size())));
        word.setText(hangmanGame.getWord());
        hangmanPicture.setImage(IMAGES[0]);
        triedLetters.setText("");
        hangmanGame.addGameStateListener((observable, oldValue, newValue) -> {
            if(oldValue == newValue)
                return;
            switch(newValue) {
                case WON:
                    textError.setText("Tu as gagné");
                    break;
                case LOST:
                    textError.setText("Tu as perdu");
                    break;
            }
        });
        hangmanGame.addMistakesListener((observable, oldValue, newValue) -> {
            if(Objects.equals(oldValue, newValue))
                return;
            hangmanPicture.setImage(IMAGES[newValue.intValue()]);
        });
        hangmanGame.addGuessedLettersListener(change -> {
            word.setText(hangmanGame.getWord());
            triedLetters.setText(hangmanGame.getTriedLetters());
        });
    }

    @FXML
    void checkLetter(ActionEvent event) {
        textError.setText("");
        if(hangmanGame == null)
            startNewGame();
        String inputString = letterToGuess.getText();
        if(letterToGuess.getText() == null || letterToGuess.getText().isEmpty()) {
            textError.setText("Remplissez une lettre d'abord");
            return;
        }
        char letter = inputString.charAt(0);
        try {
            hangmanGame.guess(letter);
        } catch(Exception e) {
            textError.setText(e.getMessage());
        }
    }

    @FXML
    void newGame(ActionEvent event) {
        startNewGame();
    }
}
