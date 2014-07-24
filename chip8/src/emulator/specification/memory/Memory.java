package emulator.specification.memory;

import emulator.specification.font.Font;

/**
 * Created by vicboma on 03/07/14.
 */
public class Memory {

    public static final int INITIALIZE = 0x0000;
    public static final int START_CHIP8_PROGRAMS = 0x0200;
    public static final int START_ETI_660_CHIP8_PROGRAMS = 0x0600;
    public static final int SIZE = 0x1000;
    private short men[];

    public Memory() {
        men = new short[SIZE];
    }

    public void set(Integer index, short value) {
        this.men[index] = value;
    }

    public int ShiftL(Integer index, int value) {
        return men[index] << value;
    }

    public int ShiftR(Integer index, int value) {
        return this.men[index] >> value;
    }

    public short get(Integer index) {
        return this.men[index];
    }

    public void loadFont(Font font) {
        // cargo las fuentes en las primeras posiciones de memoria desempaquetandolas
        for (int i = 0; i < 40; i++) {
            final int index = i << 1;
            final int mask = 0xf0;
            set(index, (short) (font.chip8[i] & mask));
            set(index + 1, (short) ((font.chip8[i] << 4) & mask));
        }
    }
}
