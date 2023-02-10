
public class BowlingGame {
    private String game;
    private Frame[] frames;
    private Score[] scores;

    public BowlingGame(String input){
        String[] frameStrArray = input.split(" ");
        frames = new Frame[10];
        scores = new Score[10];
        for (int i=0; i<frameStrArray.length; i++){
            frames[i] = new Frame(frameStrArray[i]);
        }
    }

    public int calculateFinalScore() {
        int totalScore = 0;

        for (int i=0; i<frames.length; i++){
            Frame nextFrame = null;
            try {
                nextFrame = frames[i+1];
            }catch (ArrayIndexOutOfBoundsException e){

            }finally{
                scores[i] = new Score(frames[i], nextFrame);
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
}
