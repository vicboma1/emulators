package emulator.cpu.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 12/08/15.
 */
public class LD_A_NNN extends CommandsImpl {

    /**
     * Put value r2 into r1. 0xFA
     */
    public LD_A_NNN(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        z80.a = super.addressRead((opCode3 << 8) | opCode2, 3, 16);
    }
}


