package emulator.cartridge.memory.fisic;

import cartridge.memory.bankController.MBC3;

/**
 * Created by vicboma on 15/08/15.
 */
public class Ram implements FisicMemory {
    private MBC3 mbc3;
    private byte[] ram;

    private int offset;

    public static Ram Create() {
        return new Ram();
    }

    public static Ram Create(byte[] ram) {
        return new Ram(ram);
    }

    public Ram() {
    }

    public Ram(byte[] ram) {
        this.ram = ram;
    }

    public final byte getValue(int index) {
        return ram[index];
    }

    public final void setValue(int index, byte value) {
        ram[index] = value;
    }

    public final byte[] getObject() {
        return ram;
    }

    public final void setObject(byte[] ram) {
        this.ram = ram;
    }

    public final int getOffset() {
        return offset;
    }

    public final void setOffset(int offset) {
        this.offset = offset;
    }

    public final void bank(int bank) {
        setOffset(-0xA000 + bank * 0x2000);
    }

    public final void setMBC3(MBC3 mbc3) {
        this.mbc3 = mbc3;
    }

}
