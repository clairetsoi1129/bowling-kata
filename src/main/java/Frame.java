public class Frame {
    private char[] trials = new char[]{'0','0','0'};

    public Frame(String trialStr){
        for (int i=0; i<trialStr.length(); i++){
            this.trials[i] = trialStr.charAt(i);
        }
    }

    public char[] getTrials() {
        return trials;
    }
}
