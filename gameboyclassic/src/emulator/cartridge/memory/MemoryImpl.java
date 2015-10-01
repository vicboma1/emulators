package emulator.cartridge.memory;

import cartridge.memory.bankController.MBC1;
import cartridge.memory.bankController.MBC3;
import cartridge.memory.bankController.ReadMBC3Clock;
import cartridge.memory.fisic.FisicMemory;
import framework.injector.api.annotation.Inject;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 18/08/15.
 */
public class MemoryImpl implements Memory {

    public static final String UNMAPPED = "Unmapped";
    public static final String MEMORY = "memory";
    public static final String UNMAPPED_READ_MEMORY = UNMAPPED + " read " + MEMORY + " ";
    public static final String UNMAPPED_WRITE_MEMORY = UNMAPPED + " write " + MEMORY + " ";

    private MBC3 mbc3;
    private MBC1 mbc1;

    @Inject
    public framework.logger.api.Logger logger;

    public static Memory Create() {
        return new MemoryImpl();
    }

    public static Memory Create(final MBC1 mbc1, final MBC3 mbc3) {
        return new MemoryImpl(mbc1, mbc3);
    }

    public MemoryImpl() {
    }

    public MemoryImpl(final MBC1 mbc1, final MBC3 mbc3) {
        this.mbc1 = mbc1;
        this.mbc3 = mbc3;
    }

    /**
     * address < 0x2000  // set ram enable
     * address < 0x3000  // rom select (ls byte)
     * address < 0x4000  // rom select (ms byte on mbc5, ls byte otherwise)
     * address < 0x6000  // ram select
     * address < 0x8000 && isMBC1 // RAM/ROM select (MBC1 only)
     * address < 0x8000  // MBC3 clock latch/release
     * address < 0xC000 && address >= 0xA000  // cart RAM
     *
     * @param address
     * @param data
     * @param rom
     * @param ram
     * @return
     */
    @Override
    public final void write(final int address, final int data, final FisicMemory rom, final FisicMemory ram) {

        return ;
    }

    @Override
    public final CompletableFuture<Short> read(final int address, final FisicMemory rom, final FisicMemory ram) {

        return CompletableFuture.completedFuture((short) 0);
    }


    //http://gbdev.gg8.se/wiki/articles/MBC3#The_Clock_Counter_Registers
    private Map<Integer, ReadMBC3Clock> mapMBC3reg = new Hashtable() {
        {
            put(0x09, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((short) cal.get(Calendar.MINUTE)));
            put(0x0A, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((short) cal.get(Calendar.HOUR)));
            put(0x0B, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((short) cal.get(Calendar.DAY_OF_YEAR)));
            put(0x0C, (ReadMBC3Clock) (cal) -> CompletableFuture.completedFuture((short) ((byte) cal.get(Calendar.DAY_OF_YEAR) >> 8)));
        }
    };

}
