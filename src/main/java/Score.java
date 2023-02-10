public class Score {
    private final char STRIKE = 'X';
    private final char SPARE = '/';
    private final char MISSING = '-';

    private final int ALL_KNOCKED = 10;
    private Frame frame;

    public Score(Frame frame) {
        this.frame = frame;

    }

    public int calculateFrameScore(){
        int score = 0;
        char[] trials = frame.getTrials();

        for (int i=0; i<trials.length; i++){
            switch (trials[i]){
                case STRIKE:
                    break;
                case SPARE:
                    break;
                case MISSING:
                    break;
                default: // 1-9
                    score += trials[i] - '0';
                    break;
            }
        }

        return score;
    }
}
