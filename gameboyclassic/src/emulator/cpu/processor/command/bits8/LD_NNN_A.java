package emulator.cpu.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 14/08/15.
 */
public class LD_NNN_A extends CommandsImpl {

    /**
     * Put value A into n. 0xEA
     *
     * @param z80
     */
    public LD_NNN_A(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        super.addressWrite((opCode3 << 8) | opCode2, z80.a, 3, 16);
    }
}
