package emulator.cartridge.memory.bankController;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.logger.api.Logger;

/**
 * Created by vicboma on 15/08/15.
 */
public class MBC3Impl implements MBC3 {

    private boolean clockenabled;
    private long time;
    private int reg;

    @Inject
    public Logger logger;

   /**
     * Memory bank controller
     */
    public MBC3Impl() {
        this.clockenabled = false;
        this.time = 0;
        this.reg = 0;
    }

    @PostConstruct
    public void initialize(){
        logger.info(this);
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

