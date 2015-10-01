package emulator.cartridge.memory.bankController;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.logger.api.Logger;

/**
 * Created by vicboma on 15/08/15.
 */
public class MBC1Impl implements MBC1 {

    private boolean isModeEnabled;
    private boolean isRamEnabled;

    @Inject
    public Logger logger;

    /**
     * Memory bank controller
     */
    public MBC1Impl() {
        isModeEnabled = true;
        isRamEnabled = false;
    }

    @PostConstruct
    public void initialize(){
        logger.info(this);
    }

    @Override
    public boolean isModeEnabled() {
        return isModeEnabled;
    }

    @Override
    public void setModeEnabled(boolean isMode) {
        this.isModeEnabled = isMode;
    }

    @Override
    public boolean isRamEnabled() {
        return isRamEnabled;
    }

    @Override
    public void setRamEnabled(boolean isRamEnabled) {
        this.isRamEnabled = isRamEnabled;
    }

    @Override
    public void dispose() {
        isModeEnabled = false;
        isRamEnabled = false;
    }

    @Override
    public void reset() {
        isModeEnabled = true;
        isRamEnabled = false;
    }
}
