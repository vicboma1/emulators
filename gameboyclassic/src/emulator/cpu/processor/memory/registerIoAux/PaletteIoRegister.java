package emulator.cpu.processor.memory.registerIoAux;

/**
 * Created by vicboma on 08/09/15.
 */
public class PaletteIoRegister {
    private byte OBJ0;
    private byte OBJ1;
    private byte BGP;

    public PaletteIoRegister() {
    }

    public byte OBJ0() {
        return this.OBJ0;
    }

    public void OBJ0(byte OBJ0) {
        this.OBJ0 = OBJ0;
    }

    public byte OBJ1() {
        return this.OBJ1;
    }

    public void OBJ1(byte OBJ1) {
        this.OBJ1 = OBJ1;
    }

    public byte BGP() {
        return this.BGP;
    }

    public void BGP(byte BGP) {
        this.BGP = BGP;
    }

}
