package emulator.cpu.processor.processor.command.bits8;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 12/08/15.
 */
public class LD_DE_A extends CommandsImpl {

    /**
     * ut value A into n.
     */
    public LD_DE_A(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        super.addressWriteWord(((z80.d & 0x00FF << 8) | z80.e & 0x00FF), z80.a);
    }
}
