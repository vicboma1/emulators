package emulator.cpu.processor.command;

import cpu.processor.Z80;

/**
 * Created by vicboma on 13/08/15.
 */
public class CommandsImpl implements Commands {

    protected Z80 z80;

    public CommandsImpl(Z80 z80) {
        this.z80 = z80;
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {

    }

    protected void NoOp() {
        this.z80.cycles += 4;
        this.z80.pc++;
    }

    protected int loadImmediate(final int reg) {
        this.z80.pc += 2;
        this.z80.cycles += 8;
        return reg;
    }

    protected int load(final int reg) {
        this.z80.cycles += 4;
        this.z80.pc++;
        return reg;
    }

}
