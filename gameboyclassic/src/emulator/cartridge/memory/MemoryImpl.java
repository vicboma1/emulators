package emulator.cartridge.memory;

import cartridge.game.Game;
import cartridge.game.GameImpl;
import cartridge.memory.bankController.MBC1;
import cartridge.memory.bankController.MBC3;
import cartridge.memory.fisic.FisicMemory;
import utils.Logger;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 18/08/15.
 */
public class MemoryImpl implements Memory{

    private MBC3 mbc3;
    private MBC1 mbc1;
    private Game game;

    public static Memory Create() { return new MemoryImpl(); }
    public static Memory Create(final MBC1 mbc1, final MBC3 mbc3, final Game game){ return new MemoryImpl(mbc1,mbc3, game);}

    public MemoryImpl() {
    }

    public MemoryImpl(final MBC1 mbc1,final MBC3 mbc3, final Game game) {
        this.mbc1 = mbc1;
        this.mbc3 = mbc3;
        this.game = game;
    }

    @Override
    public final CompletableFuture write(final int address,final int data, final FisicMemory rom, final FisicMemory ram ) {

        if (address < 0x2000) {
            boolean isRamEnabled = (data & 0x0F) == 0x0A;
            this.mbc1.setRamEnabled(isRamEnabled);
            return CompletableFuture.completedFuture("");
        }

        final int type = rom.getValue(GameImpl.CARTRIDGE_TYPE) & 0xFF;
        boolean isMBC5 = (type >= 0x19 && type <= 0x1E); //MBC5

        // rom select (ls byte)
        if (address < 0x3000) {
            final int mask = data & 0xFF;
            if (mask > this.game.romBanks())
                System.out.println("rom select (ls byte):" + mask + " (" + (mask < this.game.romBanks() ? "valid" : "BAD!!") + ")");

            rom.bank((!isMBC5 && data == 0) ? 1 : mask);

            return CompletableFuture.completedFuture("");
        }

        // rom select (ms byte on mbc5, ls byte otherwise)
        if (address < 0x4000) {
            if (!isMBC5)
                ram.bank(data);

            return CompletableFuture.completedFuture("");
        }

        // ram select
        if (address < 0x6000) {
            if (data < 0x04) {
                if ((data & 0xFF) > this.game.ramBanks()) {
                    System.out.println("ram switch:" + data + " (" + ((data & 0xFF) < this.game.ramBanks() ? "valid" : "BAD!!") + ")");
                }
                ram.bank(data & 0xFF);
                this.mbc3.setClockenabled(false);
            } else {
                this.mbc3.setReg(data);
                this.mbc3.setClockenabled(true);
            }
            return CompletableFuture.completedFuture("");
        }

        // RAM/ROM select (MBC1 only)
        final boolean isMBC1 = (type >= 0x01 && type <= 0x03);
        if (address < 0x8000 && isMBC1) {
            mbc1.setModeEnabled((data & 1) == 0);
            return CompletableFuture.completedFuture("");
        }

        // MBC3 clock latch/release
        if (address < 0x8000) {
            mbc3.setTime(System.currentTimeMillis());
            return CompletableFuture.completedFuture("");
        }
        // cart RAM
        if (address < 0xC000 && address >= 0xA000) {
            if (this.mbc1.isRamEnabled())
                ram.setValue(address + ram.getOffset(), (byte) data);
            /*else {
                Logger.Debug("Disabled RAM, not permits write");
            }*/
            return CompletableFuture.completedFuture("");
        }

        return CompletableFuture.completedFuture("");
    }

    @Override
    public final CompletableFuture<Byte> read(final int address, final FisicMemory rom, final FisicMemory ram) {
        if (address <= 0x3FFF)
            return CompletableFuture.completedFuture(rom.getValue(address));

        if (address <= 0x7FFF)
            return CompletableFuture.completedFuture(rom.getValue(address + rom.getOffset()));

        if (address <= 0xBFFF) {
            if (!mbc3.isClockenabled()) {
                return CompletableFuture.completedFuture(ram.getValue(address + ram.getOffset()));
            } else {
                final Calendar cal = Calendar.getInstance();
                final int regMBC3 = mbc3.getReg();
                if (mapMBC3reg.containsKey(regMBC3))
                    return mapMBC3reg.get(regMBC3).runAsync(cal);
                else
                    return CompletableFuture.completedFuture((byte) 0);
            }
        }

        Logger.Error("Read from unmapped cart memory:" + Integer.toHexString(address));
        return CompletableFuture.completedFuture((byte) 0);
    }

    interface ReadMBC3Clock {
        CompletableFuture<Byte> runAsync(final Calendar cal);
    }

    //http://gbdev.gg8.se/wiki/articles/MBC3#The_Clock_Counter_Registers
    private Map<Integer, ReadMBC3Clock> mapMBC3reg = new Hashtable() {
        {
            put(0x08, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((byte) cal.get(Calendar.SECOND)));
            put(0x09, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((byte) cal.get(Calendar.MINUTE)));
            put(0x0A, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((byte) cal.get(Calendar.HOUR)));
            put(0x0B, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((byte) cal.get(Calendar.DAY_OF_YEAR)));
            put(0x0C, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((byte) ((byte) cal.get(Calendar.DAY_OF_YEAR) >> 8)));
        }
    };

}
