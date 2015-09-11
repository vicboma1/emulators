package emulator.cpu.processor.memory.read;

import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 20/08/15.
 */
public interface MemoryMapReadInternal {
    CompletableFuture<Short> read(final int address);
}