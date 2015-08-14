package emulator.cpu.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 14/08/15.
 */
public class LDI_A_HL extends CommandsImpl {

    /**
     * Put value at address HL into A. Increment HL. Same as: LD A,(HL) - INC HL - 2A
     */
    public LDI_A_HL(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        z80.a = super.addressRead(z80.hl, 1, 8);
        super.incremetRegisterHL();
    }
}
;