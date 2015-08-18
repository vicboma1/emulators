package emulator.cartridge;

import cartridge.battery.Battery;
import cartridge.game.Game;
import cartridge.game.GameImpl;
import cartridge.memory.Memory;
import cartridge.memory.MemoryImpl;
import cartridge.memory.bankController.MBC1;
import cartridge.memory.bankController.MBC3;
import cartridge.memory.fisic.FisicMemory;
import cartridge.memory.fisic.Ram;
import utils.FileAsync;
import utils.Logger;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 13/07/14.
 */
public class CartridgeImpl implements CartridgeAsync {

    private Game game;
    private MBC1 mbc1;
    private MBC3 mbc3;
    private FisicMemory rom;
    private FisicMemory ram;
    private Battery battery;
    private Memory memory;


    public CartridgeImpl(final Game game,final FisicMemory rom,final FisicMemory ram,final MBC1 mbc1,final MBC3 mbc3,final Memory memory,final Battery battery) {
        this.game = game;
        this.rom = rom;
        this.ram = ram;
        this.mbc1 = mbc1;
        this.mbc3 = mbc3;
        this.memory = memory;
        this.battery = battery;
    }


    public final int gBIndicator() {
        return this.game.indicator();
    }

    public final boolean isGbColor() {
        return this.game.isGbColor();
    }

    public final CompletableFuture load(String romFileName, Component a) {
        try {

            FileAsync.read(romFileName, 0x04000)
                    .thenAcceptAsync((obj) -> {

                        final ByteBuffer buffer = obj;
                        this.rom.setObject(buffer.array());
                        this.game = GameImpl.Create(rom, romFileName);
                        this.memory = MemoryImpl.Create(mbc1,mbc3, game);

                        final int newMemoryWithBanks = 0x04000 * this.game.romBanks();

                        try {
                            FileAsync.read(romFileName, newMemoryWithBanks)
                                    .thenAcceptAsync((_obj) -> {

                                        final ByteBuffer _buffer = _obj;
                                        this.rom.setObject(_buffer.array());

                                        this.game.normalizeRom(rom);

                                        this.battery.loadRam(this.game)
                                                .thenAcceptAsync((objRam) -> {
                                                    if (objRam == null)
                                                        new Window((Frame) a, "Warning", "This cartridge has not loaded battery ram.", "It may not execute correctly.");

                                                    final ByteBuffer bufferRam = objRam;
                                                    this.ram.setObject(bufferRam.array());
                                                    ((Ram) this.ram).setMBC3(this.mbc3);

                                                    final boolean resCheckSum = this.game.checkSum();

                                                    //check
                                                    if (!resCheckSum && (a instanceof Frame))
                                                        new Window((Frame) a, "Warning", "This cartridge has an invalid checksum.", "It may not execute correctly.");

                                                    Logger.Debug(this.game.toString());
                                                    CompletableFuture.completedFuture("");
                                                });

                                    });
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

        return CompletableFuture.completedFuture("");
    }

    /**
     * Returns the byte currently mapped to a CPU address.
     * Addr must be in the range 0x0000 - 0x4000 or
     * 0xA000 - 0xB000 (for RAM access)
     */
    public final CompletableFuture<Byte> read(final int addr) {
        return this.memory.read(addr, this.rom, this.ram);
    }


    /**
     * Writes a byte to an address in CPU address space.  Identical to write() except that
     * writes to ROM do not cause a mapping change, but actually write to the ROM.  This is usefull
     * for patching parts of code.  Only used by the debugger.
     */
    public void debuggerAddressWrite(int addr, int data) {
        if (this.game.byteToHexCartridge() == 0x00) {
            rom.setValue(addr, (byte) data);
        } else {
            if (addr < 0x4000) {
                rom.setValue(addr, (byte) data);
            } else {
                rom.setValue(this.rom.getOffset() + addr - 0x4000, (byte) data);
            }
        }
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
    public final CompletableFuture write(int addr, int data) {
        return this.memory.write(addr, data, this.rom, this.ram);
    }

    @Override
    public void dispose() {
        this.mbc1.dispose();
        this.mbc3.dispose();
        this.battery.saveRam(this.game, this.ram)
                .thenAcceptAsync(objSaveRam -> {
                    if (objSaveRam != null)
                        Logger.Error("Dispose " + this.getClass());

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
