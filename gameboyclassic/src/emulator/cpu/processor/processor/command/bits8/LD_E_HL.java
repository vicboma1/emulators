package emulator.cpu.processor.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 12/08/15.
 */
public class LD_E_HL extends CommandsImpl {

    /**
     * Put value r2 into r1.
     */
    public LD_E_HL(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        z80.e = super.addressRead(z80.hl);
    }
}
