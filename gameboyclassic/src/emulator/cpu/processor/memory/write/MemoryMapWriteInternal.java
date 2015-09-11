package emulator.cpu.processor.memory.write;

import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 20/08/15.
 */
public interface MemoryMapWriteInternal {
    void write(final int address, final int data) throws ExecutionException, InterruptedException;
}