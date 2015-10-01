package emulator.cartridge.memory.fisic;

/**
 * Created by vicboma on 13/09/15.
 */
public class FisicMemoryImpl implements FisicMemory {
    protected byte[] mem;

    protected int offset;

    public static FisicMemoryImpl Create() {
        return new FisicMemoryImpl();
    }

    public static FisicMemoryImpl Create(byte[] mem) {
        return new FisicMemoryImpl(mem);
    }

    public FisicMemoryImpl() {
    }

    public FisicMemoryImpl(byte[] mem) {
        this.mem = mem;
    }

    public byte getData(int addres) {
        return mem[addres];
    }

    public void setData(int addres, byte value) {
        mem[addres] = value;
    }

    public byte[] getObject() {
        return mem;
    }

    public void setObject(byte[] mem) {
        this.mem = mem;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void bank(int bank) {
    }
}
