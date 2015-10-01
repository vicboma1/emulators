package emulator.cartridge.memory.bankController;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 20/08/15.
 */
public interface ReadMBC3Clock {
    CompletableFuture<Short> runAsync(final Calendar cal);
}