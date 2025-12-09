package org.howard.edu.lsp.finale.question1;

/**
 * strategy interface for all  the password-generation algorithms.
 */
public interface PasswordAlgorithm {
    /**
     * generates a password of the specified length.
     * @param length the desired password length
     * @return generated password string
     */
    public String generate(int length);
}
