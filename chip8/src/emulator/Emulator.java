package emulator;

import emulator.dispose.Disposable;

/**
 * Created by vicboma on 03/07/14.
 */
public abstract class Emulator implements Disposable {

    public Emulator() {
    }

    public abstract void initialize();

    public abstract void loadContent();
}
