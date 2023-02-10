import java.util.regex.Pattern;

public class BowlingGame {
    @SuppressWarnings("FieldMayBeFinal")
    private Frame[] frames;
    @SuppressWarnings("FieldMayBeFinal")
    private Score[] scores;

    @SuppressWarnings("FieldCanBeLocal")
    private final int MAX_FRAMES = 10;

    public BowlingGame(String input) {
        if (!validateInput(input))
            throw new IllegalArgumentException("Input is not valid.");

        String[] frameStrArray = input.split(" ");
        frames = new Frame[MAX_FRAMES];
        scores = new Score[MAX_FRAMES];
        for (int i = 0; i < frameStrArray.length; i++) {
            frames[i] = new Frame(frameStrArray[i]);
        }
    }

    public int calculateFinalScore() {
        int totalScore = 0;

        for (int i = 0; i < frames.length; i++) {
            Frame nextFrame = null;
            Frame afterNextFrame = null;
            //noinspection CatchMayIgnoreException
            try {
                nextFrame = frames[i + 1];
                afterNextFrame = frames[i + 2];
            } catch (ArrayIndexOutOfBoundsException e) {

            } finally {
                scores[i] = new Score(frames[i], nextFrame, afterNextFrame);
                totalScore += scores[i].calculateFrameScore();
            }
        }

        // Assumption:
        // input - 10 frames, each frame has 1-3 trials
        // output - total score

        // Approach:
        // 1 - Simple, i.e. no strike, no spare
        // 2 - with spare case in first 9th frames
        // 3 - with strike case in first 9th frames
        // 4 - bonus case at 10th round
        // 4a - spare cae
        // 4b - strike case
        // 5 - validation of input

        return totalScore;
    }

    private boolean validateInput(String input) {
        boolean result = false;
        if (input != null && !"".equals(input)) {
            String regex = "^([-1-9X][-1-9/]{0,1}\s|[X\s]){9}(X[-1-9X/]{1,2}|[-1-9]/[-1-9X]|[-1-9][-1-9/])$";
            result = Pattern.compile(regex).matcher(input).matches();
        }
        return result;
    }
}
