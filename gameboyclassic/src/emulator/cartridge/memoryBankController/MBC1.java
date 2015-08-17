package emulator.cartridge.memoryBankController;

import utils.Diposable.Disposable;
import utils.Reseteable.Reseteable;

/**
 * Created by vicboma on 15/08/15.
 */
public class MBC1 implements Disposable, Reseteable {
    private boolean isModeEnabled;
    private boolean isRamEnabled;

    public static MBC1 Create(){
        final boolean isModeEnabled1 = true;
        final boolean isRamEnabled1 = false;
        return new MBC1(isModeEnabled1, isRamEnabled1);
    }
    /**
     * @param isModeEnabled
     * @param isRamEnabled
     */
    public MBC1(boolean isModeEnabled, boolean isRamEnabled) {
        this.isModeEnabled = isModeEnabled;
        this.isRamEnabled = isRamEnabled;
    }

    public boolean isModeEnabled() {
        return isModeEnabled;
    }

    public void setModeEnabled(boolean isMode) {
        this.isModeEnabled = isMode;
    }

    public boolean isRamEnabled() {
        return isRamEnabled;
    }

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
