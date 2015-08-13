package emulator.cpu.processor.command.bits8.load_nn_n;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 12/08/15.
 */
public class LD_C extends CommandsImpl {

    /**
     * Put value nn into n
     */
    public LD_C(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        z80.c = super.loadImmediate(opCode2);
    }
}
