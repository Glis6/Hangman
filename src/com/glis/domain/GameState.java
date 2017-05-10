package com.glis.domain;

import sun.plugin.dom.exception.InvalidStateException;

/**
 * @author Glis
 */
public enum GameState {
    PLAYING {
        /**
         * {@inheritDoc}
         * @exception IllegalArgumentException Thrown when the letter has been guessed before.
         */
        @Override
        public void guess(HangmanGame hangmanGame, char letter) throws Exception {
            if(hangmanGame.hasBeenGuessed(letter))
                throw new IllegalArgumentException("Cette lettre a déjà été essayé");
            hangmanGame.addGuessedLetter(letter);
            if(hangmanGame.letterInWord(letter)) {
                if(hangmanGame.fullyGuessed()) {
                    hangmanGame.setGameState(WON);
                }
            } else {
                hangmanGame.addMistake();
                if(hangmanGame.livesLeft() <= 0) {
                    hangmanGame.setGameState(LOST);
                }
            }
        }
    },
    WON {
        /**
         * {@inheritDoc}
         * @throws InvalidStateException Thrown automatically
         */
        @Override
        public void guess(HangmanGame hangmanGame, char letter) throws Exception {
            throw new InvalidStateException("Vous avez déjà gagné");
        }
    },
    LOST {
        /**
         * {@inheritDoc}
         * @throws InvalidStateException Thrown automatically
         */
        @Override
        public void guess(HangmanGame hangmanGame, char letter) throws Exception {
            throw new InvalidStateException("Malheureusement, vous avez perdu");
        }
    };

    /**
     * Guesses a given letter.
     *
     * @param letter The letter being guessed.
     * @param hangmanGame The game where the letter is being guessed.
     */
    public abstract void guess(HangmanGame hangmanGame, char letter) throws Exception;
}
