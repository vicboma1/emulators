package emulator.cpu.processor.command.bits8.load_r1_r2;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 12/08/15.
 */
public class LD_A_HL extends CommandsImpl {

    /**
     * Put value r2 into r1.
     */
    public LD_A_HL(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        z80.a = super.load(( (z80.hl & 0xFF00) << 8 | (z80.hl & 0x00FF)));
        this.z80.cycles += 4;  //suma 8 ciclos
    }
}
