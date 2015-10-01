package emulator.cartridge.battery;

import cartridge.game.Game;
import cartridge.memory.fisic.FisicMemory;
import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import utils.FileAsync;
import utils.shell.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 17/08/15.
 */
public class BatteryImpl implements Battery {

    public static final String SAV = ".sav";
    public static final String SAVE_RAM_FILE = "Save Async RAM file!";
    public static final String DOING_SAVING_RAM_TO_FILE = "Doing!... Saving async RAM to file...!";
    public static final String BATTERY_RAM_FROM = "Battery RAM from ";
    public static final String DOING_READING_SRAM_FROM = "Doing..! Reading async RAM from ";

    @Inject
    public framework.logger.api.Logger logger;

    public BatteryImpl() {
    }

    @PostConstruct
    public void initialize(){
        logger.info(this);
    }

    @Override
    public final CompletableFuture<ByteBuffer> saveRam(final Game game, final FisicMemory ram) {
        if (game.ramBanks() == 0)
            return CompletableFuture.completedFuture(null);

        String name = game.getName();
        String saveRamFileName = name.substring(0, name.lastIndexOf('.')) + SAV;

        try {
            return FileAsync.write(saveRamFileName, ram.getObject());
        } catch (IOException | InterruptedException | ExecutionException e) {
            Logger.Error(SAVE_RAM_FILE);
            Logger.Error(e.getMessage());
            return CompletableFuture.completedFuture(null);
        } finally {
           Logger.Debug(DOING_SAVING_RAM_TO_FILE);
        }
    }


    @Override
    public final CompletableFuture<ByteBuffer> loadRam(final Game game) {
        String name = game.getName();
        String save = name.substring(0, name.lastIndexOf('.')) +SAV;

        final int size = 0x2000;  // 8192
        try {
            final int total = (game.ramBanks() > 1)
                    ? game.ramBanks() * size
                    : size;

            if(new File(save).exists())
                return FileAsync.read(save, total);
            else
                return CompletableFuture.completedFuture(ByteBuffer.allocate(total));
        } catch (IOException | InterruptedException | ExecutionException e) {
            Logger.Error(BATTERY_RAM_FROM + save);
            Logger.Error(e.getMessage());
            return CompletableFuture.completedFuture(null);
        } finally {
            Logger.Debug(DOING_READING_SRAM_FROM + save);
        }
    }
}
