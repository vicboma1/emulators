package emulator.cartridge.memory.bankController;

/**
 * Created by vicboma on 15/08/15.
 */
public class MBC3Impl implements MBC3 {

    private boolean clockenabled;
    private long time;
    private int reg;

    public static MBC3Impl Create(){
        final boolean clockenabled1 = false;
        final int time1 = 0;
        final int reg1 = 0;
        return new MBC3Impl(clockenabled1, time1, reg1);
    }

    /**
     * Memory bank controller
     * @param clockenabled
     * @param time
     * @param reg
     */
    public MBC3Impl(boolean clockenabled, long time, int reg) {
        this.clockenabled = clockenabled;
        this.time = time;
        this.reg = reg;
    }


    @Override
    public boolean isClockenabled() {
        return clockenabled;
    }

    @Override
    public void setClockenabled(boolean clockenabled) {
        this.clockenabled = clockenabled;
    }

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int getReg() {
        return reg;
    }

    @Override
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

