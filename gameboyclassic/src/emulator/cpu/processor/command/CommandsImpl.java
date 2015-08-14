package emulator.cpu.processor.command;

import cpu.processor.Z80;
import utils.Operation;

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
        this.z80.pc++;
        this.z80.cycles += 4;
    }

    protected int loadImmediateF(final int reg) {
        this.z80.pc += 2;
        this.z80.cycles += 16;
        return reg;
    }

    protected int loadImmediate(final int reg) {
        this.z80.pc += 2;
        this.z80.cycles += 8;
        return reg;
    }

    protected int load(final int reg) {
        this.z80.pc++;
        this.z80.cycles += 4;
        return reg;
    }

    public void addressWrite(final int address, final int data, final int opc, final int cycles) {
        z80.pc += opc;
        z80.addressWrite(address, data);
        this.z80.cycles += cycles;
    }

    public short addressRead(final int address, final int pc, final int cycles) {
        z80.pc = pc;
        this.z80.cycles += cycles;
        return Operation.unsign(z80.addressRead(address));
    }

    public void decreaseRegisterHL() {
        this.z80.hl = (z80.hl - 1) & 0xFFFF;
    }

    public void incremetRegisterHL() {
        this.z80.hl = (z80.hl + 1) & 0xFFFF;
    }
}
