package emulator.cartridge.game;

import cartridge.memory.fisic.FisicMemory;

/**
 * Created by vicboma on 18/08/15.
 */
public interface Game {
    void normalizeRom(FisicMemory rom);

    Boolean checkSum();

    int romBanks();

    int byteToHexCartridge();

    int ramBanks();

    String getName();

    int indicator();

    boolean isGbColor();

    String toString();
}
