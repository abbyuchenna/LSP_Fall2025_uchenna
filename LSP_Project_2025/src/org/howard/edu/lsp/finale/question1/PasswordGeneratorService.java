package org.howard.edu.lsp.finale.question1;

/**
 * service class for the password generation.
 * implements Singleton + Strategy patterns s
 */
public class PasswordGeneratorService {

    // singleton instance
    private static PasswordGeneratorService instance = null;

    // current algorithm (Strategy)
    private PasswordAlgorithm algorithm = null;

    /**
     * prrivate constructor for Singleton.
     */
    private PasswordGeneratorService() {}

    /**
     * returns the single shared instance of this service.
     * @return PasswordGeneratorService instance
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }

    /**
     * this selects the password generation algorithm by name.
     * @param name is algorithm identifier
     */
    public void setAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "basic":
                this.algorithm = new BasicPasswordAlgorithm();
                break;
            case "enhanced":
                this.algorithm = new EnhancedPasswordAlgorithm();
                break;
            case "letters":
                this.algorithm = new LettersOnlyPasswordAlgorithm();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }

    /**
     * generates a password using the currently selected algorithm.
     * @param length desired password length
     * @return generated password
     */
    public String generatePassword(int length) {
        if (algorithm == null) {
            throw new IllegalStateException("Algorithm must be selected before generating a password.");
        }
        return algorithm.generate(length);
    }

    /**
     * testing:
     * resets the active algorithm so tests can verify exception behavior.
     */
    protected void resetForTesting() {
        this.algorithm = null;
    }

    /*
     * =============================================================
     *              My design pattern documentation
     * =============================================================
     *
     * 1. design patterns used:
     *    - singleton Pattern
     *    - strategy Pattern
     *
     * 2. justfication:
     *
     *    **Singleton Pattern**
     *    the system will requires a single shared access point for password
     *    generation. the Singleton pattern ensures only one instance of
     *    passwordGeneratorService exists and can be accessible globally.
     *
     *    **strategy Pattern**
     *    each password-generation approach is encapsulated in a separate
     *    class implementing the PasswordAlgorithm interface. the selected
     *    algorithm can be swapped at runtime using setAlgorithm(), and the new
     *    algorithms can and will be added without modifying the client code.
     *
     * =============================================================
     */
}