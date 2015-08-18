package emulator.cartridge.memory.fisic;

/**
 * Created by vicboma on 15/08/15.
 */
public class Rom implements FisicMemory {

    private int	offset;

    private byte[] rom;

    public static Rom Create() {
        return new Rom();
    }

    public static Rom Create(byte[] rom) {
        return new Rom(rom);
    }

    public Rom() {
    }

    public Rom(byte[] rom) {
        this.rom = rom;
    }

    public final byte getValue(int index) {
        return rom[index];
    }

    public final void setValue(int index, byte value) {
        rom[index] = value;
    }

    public final byte[] getObject(){
        return rom;
    }

    public final void setObject(byte[] rom) {
        this.rom = rom;
    }

    public final int getOffset() { return offset; }

    public final void setOffset(int offset) { this.offset = offset; }

    public final void bank(int bank) { setOffset((bank - 1) * 0x4000); }

}
