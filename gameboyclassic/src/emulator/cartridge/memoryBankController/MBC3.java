package emulator.cartridge.memoryBankController;

import utils.Diposable.Disposable;
import utils.Reseteable.Reseteable;

/**
 * Created by vicboma on 15/08/15.
 */
public class MBC3 implements Disposable, Reseteable {

    private boolean clockenabled;
    private long time;
    private int reg;

    public static MBC3 Create(){
        final boolean clockenabled1 = false;
        final int time1 = 0;
        final int reg1 = 0;
        return new MBC3(clockenabled1, time1, reg1);
    }

    public MBC3(boolean clockenabled, long time, int reg) {
        this.clockenabled = clockenabled;
        this.time = time;
        this.reg = reg;
    }


    public boolean isClockenabled() {
        return clockenabled;
    }

    public void setClockenabled(boolean clockenabled) {
        this.clockenabled = clockenabled;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    @Override
     public void reset() {
        clockenabled = false;
    }

    @Override
    public void dispose() {
        clockenabled = false;
        this.reg = 0;
        this.time = 0;
    }
}

