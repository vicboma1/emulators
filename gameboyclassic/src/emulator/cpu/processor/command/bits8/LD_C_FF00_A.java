package emulator.cpu.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 14/08/15.
 */
public class LD_C_FF00_A extends CommandsImpl {

    /**
     * Put A into address $FF00 + register C.   0xE2
     *
     * @param z80
     */
    public LD_C_FF00_A(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        super.addressWrite(0xFF00 + this.z80.a, this.z80.c, 1, 8);
    }
}
