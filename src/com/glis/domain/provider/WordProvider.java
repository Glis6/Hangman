package com.glis.domain.provider;

import com.glis.domain.Word;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author Glis
 */
public interface WordProvider {
    /**
     * @return A {@link Collection} of {@link Word}s.
     */
    Collection<Word> getAllWords();

    /**
     * @param predicate The filter to apply.
     * @return A filtered {@link Collection} of {@link Word}s.
     */
    Collection<Word> getFilteredWords(Predicate<Word> predicate);
}
