package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * algorithm that generates passwords containing letters only (A–Z, a–z).
 */
public class LettersOnlyPasswordAlgorithm implements PasswordAlgorithm {
    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    private SecureRandom random = new SecureRandom();

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(LETTERS.length());
            sb.append(LETTERS.charAt(index));
        }

        return sb.toString();
    }
}
