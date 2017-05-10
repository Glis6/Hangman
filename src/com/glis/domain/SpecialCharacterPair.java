package com.glis.domain;

/**
 * A helper class to form pairs of special characters.
 */
public class SpecialCharacterPair {
    /**
     * The original {@link Character}.
     */
    private final char originalChar;

    /**
     * The {@link Character} to transform to.
     */
    private final char transformToChar;

    /**
     * @param originalCharString The original {@link Character}.
     * @param transformToCharString The {@link Character} to transform to.
     */
    public SpecialCharacterPair(String originalCharString, String transformToCharString) {
        if(originalCharString.length() != 1)
            throw new IllegalArgumentException("The original character string MUST contain just one letter.");
        if(transformToCharString.length() != 1)
            throw new IllegalArgumentException("The transform character string MUST contain just one letter.");
        this.originalChar = originalCharString.charAt(0);
        this.transformToChar = transformToCharString.charAt(0);
    }

    /**
     * @return The original {@link Character}.
     */
    public char getOriginalChar() {
        return originalChar;
    }

    /**
     * @return The {@link Character} to transform to.
     */
    public char getTransformToChar() {
        return transformToChar;
    }
}
