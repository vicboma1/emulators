package emulator.cpu.processor.processor.command;

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

    public void addressWriteWord(final int address, final int data) {
        z80.pc ++;
        z80.addressWrite(address,data & 0xFF);
        this.z80.cycles += 8;
    }

    public void addressWrite(final int address, final int data) {
        z80.pc += 2;
        z80.addressWrite(address,data & 0xFF);
        this.z80.cycles += 8;
    }

    public short addressRead(final int address) {
        z80.pc ++;
        this.z80.cycles += 8;
        return Operation.unsign(z80.addressRead(address));
    }

    public short addressReadShort(final int address) {
        z80.pc += 3;
        this.z80.cycles += 16;
        return Operation.unsign(z80.addressRead(address));
    }
}
