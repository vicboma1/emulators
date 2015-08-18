package emulator.cartridge.memory.bankController;

/**
 * Created by vicboma on 15/08/15.
 */
public class MBC1Impl implements MBC1 {
    private boolean isModeEnabled;
    private boolean isRamEnabled;

    public static MBC1 Create(){
        final boolean isModeEnabled1 = true;
        final boolean isRamEnabled1 = false;
        return new MBC1Impl(isModeEnabled1, isRamEnabled1);
    }

    /**
     * Memory bank controller
     * @param isModeEnabled
     * @param isRamEnabled
     */
    public MBC1Impl(boolean isModeEnabled, boolean isRamEnabled) {
        this.isModeEnabled = isModeEnabled;
        this.isRamEnabled = isRamEnabled;
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
