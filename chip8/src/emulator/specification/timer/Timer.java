package emulator.specification.timer;

import emulator.dispose.Disposable;
import emulator.utils.Utils;

/**
 * Created by vicboma on 08/07/14.
 */
public class Timer implements Disposable {

    public static final int SIZE = 0x02;
    public static final int DELAY = Utils.ZERO;
    public static final int SOUND = 0x01;

    private short[] T;

    public Timer() {
        T = new short[SIZE];
    }

    public short get(Integer index) {
        return this.T[index];
    }

    public void set(Integer index, short value) {
        this.T[index] = value;
    }

    public void delay(short value) {
        this.T[DELAY] = value;
    }

    public void sound(short value) {
        this.T[SOUND] = value;
    }

    public short delay() {
        return this.T[DELAY];
    }

    public short sound() {
        return this.T[SOUND];
    }

    @Override
    public void dispose() {
        this.T = new short[SIZE];
    }
}
