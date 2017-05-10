package com.glis.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Glis
 */
public class HangmanGame {
    /**
     * Special characters that will be transformed to other ones when used.
     */
    private final static Map<Character, Character> SPECIAL_CHARACTERS = Stream.of(
            new SpecialCharacterPair("ç", "c"),
            new SpecialCharacterPair("é", "e"),
            new SpecialCharacterPair("è", "e")
    ).collect(Collectors.toMap(SpecialCharacterPair::getOriginalChar, SpecialCharacterPair::getTransformToChar));

    /**
     * The maximum amount of wrong guesses before the game ends.
     */
    private final static int MAX_WRONG_GUESSES = 12;

    /**
     * The state of the current game.
     */
    private ObjectProperty<GameState> gameState = new SimpleObjectProperty<>(GameState.PLAYING);

    /**
     * The word that the game is about.
     */
    private Word word;

    /**
     * The amount of wrong guessed so far.
     */
    private IntegerProperty mistakes = new SimpleIntegerProperty(0);

    /**
     * A {@link Set} of guessed letters.
     */
    private ObservableSet<Character> guessedLetters = FXCollections.observableSet(new HashSet<>());

    /**
     * @param word The word that the game is about.
     */
    public HangmanGame(Word word) {
        this.word = word;
    }

    /**
     * @param letter The letter being guessed.
     */
    public void guess(char letter) throws Exception {
        gameState.get().guess(this, SPECIAL_CHARACTERS.getOrDefault(letter, letter));
    }

    /**
     * @param letter The letter to add.
     */
    void addGuessedLetter(char letter) {
        guessedLetters.add(letter);
    }

    /**
     * @param letter The letter to check
     * @return Whether or not the letter has been guessed.
     */
    boolean hasBeenGuessed(char letter) {
        return guessedLetters.contains(letter);
    }

    /**
     * @param letter The letter to check for.
     * @return Whether or not the word has the character in it.
     */
    boolean letterInWord(char letter) {
        return word.letterInWord(letter);
    }

    /**
     * @return Whether or not all letters have been guessed.
     */
    boolean fullyGuessed() {
        return word.fullyGuessed(guessedLetters);
    }

    /**
     * @return The amount of lives the player still has.
     */
    int livesLeft() {
        return MAX_WRONG_GUESSES - mistakes.get();
    }

    /**
     * Adds a mistake.
     */
    void addMistake() {
        mistakes.set(mistakes.getValue() + 1);
    }

    /**
     * @param gameState The {@link GameState} to set.
     */
    void setGameState(GameState gameState) {
        this.gameState.set(gameState);
    }

    /**
     * @return The word converted with the letters already guessed.
     */
    public String getWord() {
        return word.getWord(guessedLetters);
    }

    /**
     * @return A string combination of letters that have been tried.
     */
    public String getTriedLetters() {
        return guessedLetters.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    /**
     * @param listener The listener to add.
     */
    public void addGameStateListener(ChangeListener<GameState> listener) {
        gameState.addListener(listener);
    }

    /**
     * @param listener The listener to add.
     */
    public void addMistakesListener(ChangeListener<Number> listener) {
        mistakes.addListener(listener);
    }

    /**
     * @param listener The listener to add.
     */
    public void addGuessedLettersListener(SetChangeListener<Character> listener) {
        guessedLetters.addListener(listener);
    }
}
