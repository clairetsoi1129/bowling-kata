public class Score {
    public static final char STRIKE = 'X';
    public static final char SPARE = '/';
    public static final char MISSING = '-';

    private final int ALL_KNOCKED = 10;
    private Frame frame;
    private Frame nextFrame;

    private int checkNextTrial = 0;

    public Score(Frame frame, Frame nextFrame) {
        this.frame = frame;
        this.nextFrame = nextFrame;
    }

    public int calculateFrameScore(){
        int score = 0;
        char[] trials = frame.getTrials();
        System.out.println("0calculateFrameScore:"+frame.toString());

        for (int i=0; i<trials.length; i++){
            switch (trials[i]){
                case STRIKE:
                    break;
                case SPARE:
                    score += ALL_KNOCKED - (trials[i-1] - '0');
                    System.out.println("3calculateFrameScore:"+score);
                    checkNextTrial = 1;
                    break;
                case MISSING:
                    break;
                default: // 1-9
                    score += trials[i] - '0';
                    System.out.println("4calculateFrameScore:"+score);
                    break;
            }
        }
        System.out.println("1calculateFrameScore:"+score);

        if (checkNextTrial == 1){
            score += calculateTrialScore(getNextTrial());
            System.out.println("2calculateFrameScore:"+score);
        }

        return score;
    }

    private int calculateTrialScore(char trial){
        int score = 0;
        switch (trial){
            case STRIKE:
                break;
            case SPARE:
                break;
            case MISSING:
                break;
            default: // 1-9
                score += trial - '0';
                break;
        }
        return score;

    }

    private char getNextTrial(){
        return nextFrame!=null?nextFrame.getTrials()[0]:'0';
    }
}
