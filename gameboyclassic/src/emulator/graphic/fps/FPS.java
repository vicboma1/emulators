package emulator.graphic.fps;

/**
 * Created by vicboma on 02/09/15.
 */
public interface FPS {
    FPS calculate();
    void start();
    Boolean count();
    Boolean validate();

    Integer get();
    Integer waitTime() ;
    void setDone(boolean done);
    Boolean done();
    int skip();
    void setSkip(int skip);
}
