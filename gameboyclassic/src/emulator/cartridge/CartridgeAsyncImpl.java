package emulator.cartridge;

import cartridge.battery.Battery;
import cartridge.game.Game;
import cartridge.game.GameImpl;
import cartridge.memory.Memory;
import cartridge.memory.MemoryImpl;
import cartridge.memory.bankController.MBC1;
import cartridge.memory.bankController.MBC3;
import cartridge.memory.fisic.Ram;
import cartridge.memory.fisic.Rom;
import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.logger.api.Logger;
import utils.CheckSum;
import utils.FileAsync;
import utils.window.Window;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 13/07/14.
 */
public class CartridgeAsyncImpl implements CartridgeAsync {

    public static final int MEMORY_ADDRESS_ROM_BANK = 0x4000;
    public static final String DISPOSE = "Dispose ";

    @Inject
    public Logger logger;
    @Inject
    public Game game;
    @Inject
    public MBC1 mbc1;
    @Inject
    public MBC3 mbc3;
    @Inject
    public Rom rom;
    @Inject
    public Ram ram;
    @Inject
    public Battery battery;
    @Inject
    public Memory memory;

    public CartridgeAsyncImpl(){}

    @PostConstruct
    public void initialize(){
        logger.info(this);
    }

    public int gBIndicator() {
        return this.game.indicator();
    }

    public boolean isGbColor() {
        return this.game.isGbColor();
    }

    public CompletableFuture load(String romFileName, Window window) {
            try {
                FileAsync.read(romFileName, MEMORY_ADDRESS_ROM_BANK)
                        .thenAcceptAsync((obj) -> {

                            final ByteBuffer buffer = obj;
                            rom.setObject(buffer.array());
                            game = GameImpl.Create(rom, romFileName);
                            memory = MemoryImpl.Create(mbc1, mbc3);

                            final int newMemoryWithBanks = MEMORY_ADDRESS_ROM_BANK * game.romBanks();

                            try {
                                newMemoryWithBanks(romFileName, window, newMemoryWithBanks);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return CompletableFuture.completedFuture(Void.TYPE);
     }

    private void newMemoryWithBanks(String romFileName, Window window, int newMemoryWithBanks) throws IOException, InterruptedException, ExecutionException {
        FileAsync.read(romFileName, newMemoryWithBanks)
                .thenAcceptAsync((_obj) -> {

                    final ByteBuffer _buffer = _obj;
                    this.rom.setObject(_buffer.array());
                    this.game.normalizeRom(rom);
                    LoadBatteryRam(window);

                });
    }

    private void LoadBatteryRam(Window window) {
        this.battery.loadRam(this.game)
                .thenAcceptAsync((objRam) -> {
                    setRam(window, objRam);
                    checkSumValidate(window);
                    toStringGameProperty();
                    CompletableFuture.completedFuture("");
                });
    }

    private void setRam(Window window, ByteBuffer objRam) {
        if (objRam == null)
            window.showDialogWarning("Battery Ram not loaded", "Maybe can not run properly");

        final ByteBuffer bufferRam = objRam;
        this.ram.setObject(bufferRam.array());
    }

    private void toStringGameProperty() {
        logger.info(this);
    }

    private void checkSumValidate(Window window) {
        final boolean validate = CheckSum.validate(this.rom.getObject());
        final boolean resCheckSum = this.game.checkSum(validate);
        if (!resCheckSum)
            window.showDialogWarning("This cartridge has not the correct Checksum", "Maybe can not run properly");
    }

    /**
     * Returns the byte currently mapped to a CPU address.
     * Addr must be in the range 0x0000 - 0x4000 or
     * 0xA000 - 0xB000 (for RAM access)
     */
    public CompletableFuture<Short> read(final int addr) {
        return this.memory.read(addr, this.rom, this.ram);
    }

    /**
     * 0000-3FFF - ROM Bank 00 (Read Only) Same as for MBC1.
     * 4000-7FFF - ROM Bank 01-7F (Read Only)
     * Same as for MBC1, except that accessing banks 20h, 40h, and 60h is supported now.
     * 0000-BFFF - RAM Bank 00-03, if any (Read/Write)
     * A000-BFFF - RTC Register 08-0C (Read/Write)
     * Depending on the current Bank Number/RTC Register selection,
     * this memory space is used to access an 8KByte external RAM Bank,
     * or a single RTC Register.
     */
    public void write(int addr, int data) {
         this.memory.write(addr, data, this.rom, this.ram);
    }

    @Override
    public void dispose() {
        this.mbc1.dispose();
        this.mbc3.dispose();
        this.battery.saveRam(this.game, this.ram)
                .thenAcceptAsync(objSaveRam -> {
                    if (objSaveRam != null)
                        logger.error(this, DISPOSE + this.getClass());
                    else
                        logger.debug(this, DISPOSE + this.getClass());

                });
    }


    @Override
    public void reset() {
        this.ram.bank(0);
        this.rom.bank(1);
        this.mbc1.reset();
        this.mbc3.reset();
    }
}
