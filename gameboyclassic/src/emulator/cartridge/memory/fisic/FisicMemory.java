package emulator.cartridge.memory.fisic;

/**
 * Created by vicboma on 15/08/15.
 */
public interface FisicMemory {

    byte getValue(int address);

    void setValue(int address, byte value);

    byte[] getObject();

    void setObject(byte[] memory);

    int getOffset();

    void setOffset(int offset);

    void bank(int bank);

}