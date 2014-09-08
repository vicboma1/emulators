package emulator.specification.register;

import emulator.specification.memory.Memory;
import framework.dispose.Disposable;

/**
 * Created by vicboma on 08/07/14.
 * <p>
 * short[] V  -> Registros generales del CHIP-8 (16)
 * int I      -> Index register
 * int opCode -> Actual instruction
 * int IP     -> Instruction pointer
 * int SP     -> Stack pointer
 */
public class Register implements Disposable {

    public static final int OPCODES_SIZE = 4;
    private static final int SIZE = 0x10;
    private short[] V;
    private int I;
    private int opCode;
    private short[] opCodes;
    private int IP;
    private int SP;

    public Register() {
        V = new short[SIZE];
        opCodes = new short[OPCODES_SIZE];
    }

    public short V(final int index) {
        return V[index];
    }

    public void V(final int index, Short v) {
        V[index] = v;
    }

    public void V_OR(final int index, final int index2) {
        V[index] |= V[index2];
    }

    public void V_AND(final int index, final int index2) {
        V[index] &= V[index2];
    }

    public void V_XOR(final int index, final int index2) {
        V[index] ^= V[index2];
    }

    public void V_SHR(final int index, final int value) {
        V[index] >>= value;
    }

    public int I() {
        return I;
    }

    public void I(int i) {
        I = i;
    }

    public int opCode() {
        return opCode;
    }

    public void opCode(int opCode) {
        this.opCode = opCode;
    }

    public int IP() {
        return IP;
    }

    public void IP(int IP) {
        this.IP = IP;
    }

    public int SP() {
        return SP;
    }

    public void SP(int SP) {
        this.SP = SP;
    }

    @Override
    public void dispose() {
        this.opCodes = new short[OPCODES_SIZE];
        this.V = new short[SIZE];
        this.I = Memory.INITIALIZE;
        this.IP = Memory.START_CHIP8_PROGRAMS;
        this.opCode = 0;
        this.SP = 0x01E0;
    }

    public void opCodes(final short opCode1, final short opCode2, final short opCode3, final short opCode4) {
        this.opCodes = new short[]{0, opCode1, opCode2, opCode3, opCode4};
    }

    public short getOpCode(final int index) {
        return this.opCodes[index];
    }

}
