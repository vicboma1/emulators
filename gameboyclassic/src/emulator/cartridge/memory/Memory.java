package emulator.cartridge.memory;

import cartridge.memory.fisic.FisicMemory;

import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 18/08/15.
 */
public interface Memory {

    CompletableFuture<Byte> read(final int address, final FisicMemory rom, final FisicMemory ram);

    CompletableFuture write(final int address, final int data, final FisicMemory rom, final FisicMemory ram);

}
