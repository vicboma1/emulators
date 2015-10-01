package emulator.cartridge.memory.fisic;

import utils.shell.Logger;

/**
 * Created by vicboma on 15/08/15.
 */
public class Ram extends FisicMemoryImpl {

    public static Ram Create() {
        return new Ram();
    }

    public static Ram Create(byte[] ram) {
        return new Ram(ram);
    }

    public Ram() {
    }

    public Ram(byte[] ram) {
        super(ram);
    }

    @Override
    public void setData(int addres, byte value) {
        if (addres >= 197198)
            Logger.Debug("address: " + addres + " value: " + value);
        else
            mem[addres] = value;
    }

    public void bank(int bank) {
        setOffset(-0xA000 + bank * 0x2000);
    }

}
