package emulator.cpu.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 14/08/15.
 */
public class LD_A_HLL extends CommandsImpl {

    /**
     * Put value at address HL into A. Decrement HL. Same as: LD A,(HL) - DEC HL 0x3A
     */
    public LD_A_HLL(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        super.addressRead(z80.hl, 1, 8);
        super.decreaseRegisterHL();
    }
}
