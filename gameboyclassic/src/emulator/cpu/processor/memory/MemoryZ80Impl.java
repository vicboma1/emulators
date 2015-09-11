package emulator.cpu.processor.memory;

import cpu.processor.memory.read.MemoryMapReadImpl;
import cpu.processor.memory.read.MemoryMapReadInternal;
import cpu.processor.memory.write.MemoryMapWriteImpl;
import cpu.processor.memory.write.MemoryMapWriteInternal;
import utils.shell.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 10/09/15.
 * Object value pattern
 */
public class MemoryZ80Impl implements MemoryZ80 {

    private MemoryMapReadImpl memoryMapReadImpl;
    private MemoryMapWriteImpl memoryMapWriteImpl;

    public static MemoryZ80 Create(MemoryMapReadImpl memoryMapReadImpl, MemoryMapWriteImpl memoryMapWriteImpl){
        return new MemoryZ80Impl(memoryMapReadImpl,memoryMapWriteImpl);
    }


    MemoryZ80Impl(final MemoryMapReadImpl memoryMapReadImpl, MemoryMapWriteImpl memoryMapWriteImpl) {
        this.memoryMapReadImpl = memoryMapReadImpl;
        this.memoryMapWriteImpl = memoryMapWriteImpl;
    }

    public CompletableFuture<Short> read(final int address) {
        final int keyMap = address & 0xF000;

        if (memoryMapReadImpl.containsKey(keyMap)) {
            final MemoryMapReadInternal memoryMapReadInternal = memoryMapReadImpl.get(keyMap);
            return memoryMapReadInternal.read(address);
        }

        Logger.Debug("Unmapped command to read address " + address);//  pc = " + Operation.hexWord(pc)+" .  sp = "+Operation.hexWord(sp));
        return CompletableFuture.completedFuture((short) 0xFF);

    }

    public void write(final int address, final int data) throws ExecutionException, InterruptedException {
        final int keyMap = address & 0xF000;

        if (memoryMapWriteImpl.containsKey(keyMap)) {
            final MemoryMapWriteInternal memoryMapWriteInternal = memoryMapWriteImpl.get(keyMap);
            memoryMapWriteInternal.write(address, data);
            return;
        }

        Logger.Debug("Unmapped command to write address " + address);// + ".  pc = " + Operation.hexWord(pc)+" .  sp = "+Operation.hexWord(sp));

    }
}


