import java.util.Arrays;

public class Frame {
    @SuppressWarnings("FieldMayBeFinal")
    private char[] trials = {'0','0','0'};

    public Frame(String frame) {
        for (int i=0; i<frame.length(); i++) {
            if (frame.charAt(i) != Score.MISSING) {
                this.trials[i] = frame.charAt(i);
            }
        }
    }

    public char[] getTrials() {
        return trials;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "trials=" + Arrays.toString(trials) +
                '}';
    }
}
