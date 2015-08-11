package emulator.cpu.processor.command.bits8.load;

import cpu.processor.Z80;
import cpu.processor.command.Commands;

/**
 * Created by vicboma on 12/08/15.
 */
public class LD_C implements Commands {

    /**
     * Put value nn into n
     */
    public LD_C() {
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4, Z80 z80) {
        z80.pc += 2;
        z80.c = opCode2;
    }
}