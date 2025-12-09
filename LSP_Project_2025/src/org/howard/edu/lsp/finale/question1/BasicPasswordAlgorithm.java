package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * will implements a basic digits-only password generation algorithm.
 * uses java.util.Random and generates characters from 0–9.
 */
public class BasicPasswordAlgorithm implements PasswordAlgorithm {
    private static final String DIGITS = "0123456789";
    private Random random = new Random();

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(DIGITS.length());
            sb.append(DIGITS.charAt(index));
        }

        return sb.toString();
    }
}
