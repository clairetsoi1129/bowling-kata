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
}