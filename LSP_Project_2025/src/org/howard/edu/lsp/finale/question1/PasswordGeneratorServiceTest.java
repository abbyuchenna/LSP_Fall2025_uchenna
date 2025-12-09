package org.howard.edu.lsp.finale.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * jUnit test suite for PasswordGeneratorService 
 */
public class PasswordGeneratorServiceTest {

    private PasswordGeneratorService service;

    /**
     * retrieves the singleton instance before each test.
 
     */
    @BeforeEach
    public void setup() {
        service = PasswordGeneratorService.getInstance();
    }

    /**
     * verifies that getInstance() never returns null.
     */
    @Test
    public void checkInstanceNotNull() {
        assertNotNull(service, "getInstance() should not return null.");
    }

    /**
     * will verify true singleton behavior: both references must be identical.
     */
    @Test
    public void checkSingleInstanceBehavior() {
        PasswordGeneratorService second = PasswordGeneratorService.getInstance();
        assertSame(service, second, "Both references must point to the same singleton instance.");
    }

    /**
     * will verify that calling generatePassword before selecting an algorithm 
     * throws an IllegalStateException — a required behavior in the rubric.
     */
    @Test
    public void generateWithoutSettingAlgorithmThrowsException() {
        PasswordGeneratorService s = PasswordGeneratorService.getInstance();

        assertThrows(IllegalStateException.class, () -> {
            s.generatePassword(8);
        }, "generatePassword() must throw IllegalStateException before an algorithm is selected.");
    }

    /**
     * verifies that the basic algorithm generates a digits-only password
     * of the correct length.
     */
    @Test
    public void basicAlgorithmGeneratesCorrectLengthAndDigitsOnly() {
        service.setAlgorithm("basic");
        String p = service.generatePassword(10);

        assertNotNull(p);
        assertEquals(10, p.length(), "Password length must match requested length.");
        assertTrue(p.matches("[0-9]{10}"), "Basic algorithm must generate digits only.");
    }

    /**
     * enhanced algorithm generates an alphanumeric password
     * of the correct length.
     */
    @Test
    public void enhancedAlgorithmGeneratesCorrectCharactersAndLength() {
        service.setAlgorithm("enhanced");
        String p = service.generatePassword(12);

        assertNotNull(p);
        assertEquals(12, p.length(), "Password length must match requested length.");
        assertTrue(p.matches("[A-Za-z0-9]{12}"), "Enhanced algorithm must generate alphanumeric characters only.");
    }

    /**
     * etters-only algorithm generates only alphabetic characters.
     */
    @Test
    public void lettersAlgorithmGeneratesLettersOnly() {
        service.setAlgorithm("letters");
        String p = service.generatePassword(8);

        assertNotNull(p);
        assertEquals(8, p.length(), "Password length must match requested length.");
        assertTrue(p.matches("[A-Za-z]{8}"), "Letters algorithm must generate alphabetic characters only.");
    }

    /**
     * switching between algorithms changes output behavior
     * as required: digits → letters → alphanumeric.
     */
    @Test
    public void switchingAlgorithmsChangesBehavior() {
        service.setAlgorithm("basic");
        String p1 = service.generatePassword(10);
        assertTrue(p1.matches("[0-9]{10}"), "Basic algorithm must produce digits only.");

        service.setAlgorithm("letters");
        String p2 = service.generatePassword(10);
        assertTrue(p2.matches("[A-Za-z]{10}"), "Letters algorithm must produce letters only.");

        service.setAlgorithm("enhanced");
        String p3 = service.generatePassword(10);
        assertTrue(p3.matches("[A-Za-z0-9]{10}"), "Enhanced algorithm must produce alphanumeric characters.");
    }
}
