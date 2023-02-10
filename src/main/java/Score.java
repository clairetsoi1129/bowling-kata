public class Score {
    @SuppressWarnings("FieldMayBeFinal")
    private Frame frame;

    @SuppressWarnings("FieldMayBeFinal")
    private Frame nextFrame;

    @SuppressWarnings("FieldMayBeFinal")
    private Frame afterNextFrame;

    public static final char STRIKE = 'X';
    public static final char SPARE = '/';
    public static final char MISSING = '-';

    @SuppressWarnings("FieldCanBeLocal")
    private final int ALL_KNOCKED = 10;

    private int checkNextThrow = 0;

    public Score(Frame frame, Frame nextFrame, Frame afterNextFrame) {
        this.frame = frame;
        this.nextFrame = nextFrame;
        this.afterNextFrame = afterNextFrame;
    }

    private int calculateTrialsScore(char[] trials){
        return calculateTrialsScore(trials, false);
    }

    private int calculateTrialsScore(char[] trials, boolean checkMax) {
        int score = 0;
        checkNextThrow = 0;

        for (int i = 0; i < trials.length; i++) {
            switch (trials[i]) {
                case STRIKE -> {
                    score += ALL_KNOCKED;
                    checkNextThrow = 2;
                }
                case SPARE -> {
                    score += ALL_KNOCKED - (trials[i - 1] - '0');
                    checkNextThrow = 1;
                }
                default -> score += trials[i] - '0';
            }
        }
        System.out.println("score:"+score);

        if (checkMax && score > ALL_KNOCKED){
            throw new IllegalArgumentException("Input is not valid.");
        }
        return score;
    }

    public int calculateFrameScore() {
        int frameScore = 0;
        char[] trials = frame.getTrials();
        if (nextFrame == null){ // 10th round, basic score can be more than 10
            frameScore += calculateTrialsScore(trials);
            System.out.println("1 frameScore:"+frameScore);
        }else { // 1-9th round, basic score cannot be more than 10
            frameScore += calculateTrialsScore(trials, true);
            System.out.println("2 frameScore:"+frameScore);
        }

        if (checkNextThrow == 1) {
            frameScore += calculateTrialsScore(getNextThrow());
            System.out.println("3 frameScore:"+frameScore);
        } else if (checkNextThrow == 2) {
            frameScore += calculateTrialsScore(getNextTwoThrow());
            System.out.println("4 frameScore:"+frameScore);
        }
        return frameScore;
    }

    private char[] getNextThrow() {
        // if nextFrame is null, return the first round
        char[] trials = new char[]{'0'};
        if (nextFrame != null) { // 1-9 round
            trials[0] = nextFrame.getTrials()[0];
        } // else {} // 10 th round already included in calculation
        return trials;
    }

    private char[] getNextTwoThrow() {
        char[] trials = new char[]{'0', '0'};

        if (nextFrame != null) { // 1-9 th round
            if (nextFrame.getTrials()[0] == STRIKE) {
                // if nextFrame is STRIKE,
                // get nextNextFrame first trial (1-8th round)
                // if nextNextFrame is null, then get the 2nd trials in 10th round
                trials[0] = nextFrame.getTrials()[0];
                if (afterNextFrame != null) {
                    trials[1] = afterNextFrame.getTrials()[0];
                } else {
                    trials[1] = nextFrame.getTrials()[1];
                }
            } else {
                // if nextFrame is not STRIKE, then get nextFrame seond trial
                trials[0] = nextFrame.getTrials()[0];
                trials[1] = nextFrame.getTrials()[1];
            }
        } //else {} //10 th round already included in calculation
        return trials;
    }
}
