package emulator.cartridge.memory.bankController;

import utils.Diposable.Disposable;
import utils.Reseteable.Reseteable;

/**
 * Created by vicboma on 18/08/15.
 */
public interface MBC3 extends Disposable, Reseteable {
    boolean isClockenabled();

    void setClockenabled(boolean clockenabled);

    long getTime();

    void setTime(long time);

    int getReg();

    void setReg(int reg);
}
