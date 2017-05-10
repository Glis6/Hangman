package com.glis.domain;

import com.glis.domain.provider.WordProvider;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Glis
 */
public class GenericWordProvider implements WordProvider {
    /**
     * The words that can be given to guess.
     */
    private final static Collection<String> WORDS = Arrays.asList(
            "baguette",
            "chanson",
            "parapluie",
            "avion",
            "capital",
            "canard",
            "amie",
            "elephant",
            "eau",
            "poisson"
    );

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Word> getAllWords() {
        return WORDS.stream().map(Word::new).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Word> getFilteredWords(Predicate<Word> predicate) {
        return getAllWords().stream().filter(predicate).collect(Collectors.toList());
    }
}
