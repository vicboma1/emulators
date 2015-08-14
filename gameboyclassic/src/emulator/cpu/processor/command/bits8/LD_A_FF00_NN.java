package emulator.cpu.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;
import utils.Operation;

/**
 * Created by vicboma on 14/08/15.
 */
public class LD_A_FF00_NN extends CommandsImpl {

    /**
     * Put memory address $FF00+n into A. - F0
     *
     * @param z80
     */
    public LD_A_FF00_NN(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        this.z80.a = Operation.unsign(super.addressRead(0xFF00 + opCode2, 2, 16));
    }
}
