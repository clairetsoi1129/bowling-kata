import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class BowlingGameTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void testValidCase(
            String input, int expected) {
        BowlingGame bowling = new BowlingGame(input);
        int actualValue = bowling.calculateFinalScore();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/invalidData.csv", numLinesToSkip = 1)
    void testInvalidNumeralThrowException(
            String input) {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    BowlingGame bowling = new BowlingGame(input);
                    bowling.calculateFinalScore();
                });

        String expectedMessage = "Input is not valid.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}