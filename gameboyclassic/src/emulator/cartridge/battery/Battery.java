package emulator.cartridge.battery;

import cartridge.game.Game;
import cartridge.memory.fisic.FisicMemory;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 18/08/15.
 */
public interface Battery {
    CompletableFuture<ByteBuffer> saveRam(Game game, FisicMemory ram);

    CompletableFuture<ByteBuffer> loadRam(Game game);
}
