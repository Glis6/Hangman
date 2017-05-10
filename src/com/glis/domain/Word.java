package com.glis.domain;

import java.util.Collection;

/**
 * @author Glis
 */
public class Word {
    /**
     * The word itself.
     */
    private final String word;

    /**
     * The word itself.
     */
    Word(String word) {
        this.word = word;
    }

    /**
     * @param letter The letter to check for.
     * @return Whether or not the word has the character in it.
     */
    boolean letterInWord(char letter) {
        return word.indexOf(letter) >= 0;
    }

    /**
     * @param guessedLetters All the letters guessed so far.
     * @return Whether or not all letters have been guessed.
     */
    boolean fullyGuessed(Collection<Character> guessedLetters) {
        for (char c : word.toCharArray()) {
            if(!guessedLetters.contains(c))
                return false;
        }
        return true;
    }

    /**
     * @param guessedLetters All the letters guessed so far.
     * @return The word converted with the letters already guessed.
     */
    String getWord(Collection<Character> guessedLetters) {
        StringBuilder returnString = new StringBuilder();
        for (char c : word.toCharArray()) {
            if(guessedLetters.contains(c)) {
                returnString.append(c);
            } else {
                returnString.append("_");
            }
            returnString.append(" ");
        }
        return returnString.substring(0, returnString.length() - 1);
    }
}
