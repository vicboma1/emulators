package emulator.cartridge.memory.fisic;

/**
 * Created by vicboma on 15/08/15.
 */
public class Rom extends FisicMemoryImpl {

    public static Rom Create() {
        return new Rom();
    }

    public static Rom Create(byte[] rom) {
        return new Rom(rom);
    }

    public Rom() {
    }

    public Rom(byte[] rom) {
        super(rom);
    }

    public void bank(int bank) {
        setOffset((bank - 1) * 0x4000);
    }

}
