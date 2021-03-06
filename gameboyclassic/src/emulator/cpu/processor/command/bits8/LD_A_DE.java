package emulator.cpu.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 12/08/15.
 */
public class LD_A_DE extends CommandsImpl {

    /**
     * Put value A into n.
     */
    public LD_A_DE(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        z80.a = super.addressRead((z80.d & 0x00FF) << 8 | z80.e & 0x00FF, 1, 8);
    }
}
