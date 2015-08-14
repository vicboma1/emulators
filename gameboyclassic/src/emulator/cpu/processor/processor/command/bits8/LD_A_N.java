package emulator.cpu.processor.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 12/08/15.
 */
public class LD_A_N extends CommandsImpl {

    /**
     * Put value n into A.
     */
    public LD_A_N(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        z80.a = super.loadImmediate((opCode2 & 0x00FF));
    }
}
