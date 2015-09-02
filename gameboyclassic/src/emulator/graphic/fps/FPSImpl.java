package emulator.graphic.fps;

import utils.shell.Logger;

/**
 * Created by vicboma on 02/09/15.
 */
public class FPSImpl implements FPS {

    public static final int MILLISECONDS = 1000;
    public static final int POOR_FRAME = 30;

    private boolean done;
    private int avg;
    private long elapsedTime;
    private int count;

    private int waitTime;
    private int skip;

    public static final int LOWER_SKIP = 2;
    public static final int UPPER_SKIP = 3;

    public static FPSImpl Create(){
        return new FPSImpl();
    }

    public FPSImpl() {
        skip = LOWER_SKIP;
    }

    public FPSImpl calculate() {
        initialize();

        if (isDrawnOptimum())
            watiTime();

        return this;
    }

    private void watiTime() {
        avg = average();
        elapsedTime = System.currentTimeMillis();
        int time = 100;
        if (avg != 0)
            time = MILLISECONDS / avg;

        waitTime = 10 - time + waitTime;
        Logger.Debug("" + waitTime);
        count = 0;
    }

    private boolean isDrawnOptimum() {
        return count > POOR_FRAME;
    }

    private int average() {
        final int totalElapsedTime = (int)(System.currentTimeMillis() - elapsedTime);
        final float delayAbs = totalElapsedTime / ((float) MILLISECONDS);
        final int avg = (int) ( count / delayAbs);
        return avg;
    }

    public void start(){
        elapsedTime = System.currentTimeMillis();
    }

    private void initialize() {
        if (elapsedTime == 0)
            start();
    }
    
    public Boolean count(){
        skip = (avg <= POOR_FRAME) ? UPPER_SKIP : LOWER_SKIP;
        count++;
        done = true;

        return ((count % skip) != 0);
    }

    public Boolean validate(){
        final boolean b = (count % skip) != 0;
        return b;
    }

   public Integer get() {
        return avg;
    }

    public Integer waitTime() {
        return waitTime;
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public Boolean done() {
        return done;
    }

    public int skip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }


}
