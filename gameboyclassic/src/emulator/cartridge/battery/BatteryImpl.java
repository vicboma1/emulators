package emulator.cartridge.battery;

import cartridge.game.Game;
import cartridge.memory.fisic.FisicMemory;
import utils.FileAsync;
import utils.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 17/08/15.
 */
public class BatteryImpl implements Battery {

    public static Battery Create(){ return new BatteryImpl(); }

    public BatteryImpl(){}

    @Override
    public final CompletableFuture<ByteBuffer> saveRam(final Game game, final FisicMemory ram) {
        if (game.ramBanks() == 0)
            return CompletableFuture.completedFuture(null);

        String name = game.getName();
        String saveRamFileName = name.substring(0, name.lastIndexOf('.')) + ".sav";

        try {
            return FileAsync.write(saveRamFileName, ram.getObject());
        } catch (IOException | InterruptedException | ExecutionException e) {
            Logger.Error("Saving ram to file... error!");
            Logger.Error(e.getMessage());
            return CompletableFuture.completedFuture(null);
        } finally {
            Logger.Debug("Saving ram to file... done.");
        }
    }


    @Override
    public final CompletableFuture<ByteBuffer> loadRam(Game game) {
        String name = game.getName();
        String saveRamFileName = name.substring(0, name.lastIndexOf('.')) + ".sav";

        final int size = 0x2000;  // 8192
        try {
            final int total = (game.ramBanks() > 1)
                    ? game.ramBanks() * size
                    : size;
            return FileAsync.read(saveRamFileName, total);
        } catch (IOException | InterruptedException | ExecutionException e) {
            Logger.Error("Error loading battery RAM from '" + saveRamFileName + "'");
            Logger.Error(e.getMessage());
            return CompletableFuture.completedFuture(null);
        } finally {
            Logger.Debug("Read SRAM from '" + saveRamFileName + "'");
        }
    }
}
