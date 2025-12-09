package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * enhanced password generation algorithm.
 * sses SecureRandom and supports letters + digits.
 */
public class EnhancedPasswordAlgorithm implements PasswordAlgorithm {
    private static final String ALLOWED =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    private SecureRandom random = new SecureRandom();

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALLOWED.length());
            sb.append(ALLOWED.charAt(index));
        }

        return sb.toString();
    }
}
