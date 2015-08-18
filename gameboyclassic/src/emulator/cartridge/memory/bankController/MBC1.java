package emulator.cartridge.memory.bankController;

import utils.Diposable.Disposable;
import utils.Reseteable.Reseteable;

/**
 * Created by vicboma on 18/08/15.
 */
public interface MBC1 extends Disposable, Reseteable {
    boolean isModeEnabled();

    void setModeEnabled(boolean isMode);

    boolean isRamEnabled();

    void setRamEnabled(boolean isRamEnabled);
}
