package emulator.main.loop;

/**
 * Created by vicboma on 29/09/15.
 */
public interface MainLoopCore {
    MainLoopCore queue(String option);
    void selectableOption();
}
