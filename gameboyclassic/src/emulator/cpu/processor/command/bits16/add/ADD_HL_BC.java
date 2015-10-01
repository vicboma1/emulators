package emulator.cpu.processor.command.bits16.add;

import cpu.processor.command.CommandsImpl;
import cpu.processor.register.flag.Flags;
import cpu.processor.z80.Z80Impl;

/**
 * Created by vicboma on 14/09/14.
 * Mnemonic      Time Size CNPHZS  OP-Code
 * ADD HL,BC     11    1   +0- --  09
 * HL <- HL + BC
 */
public class ADD_HL_BC extends CommandsImpl {

    public static final int OP_CODE_0X09 = 0x09;

    public ADD_HL_BC(Z80Impl z80Impl) {
        super(z80Impl);
    }

    /**
     * 4   ld c,a
     * 4   ld b,0
     * 11  add hl,bc
     * @param opCode1
     * @param opCode2
     * @param opCode3
     * @param opCode4
     */
    @Override
    public void execute(int opCode1, int opCode2, int opCode3, int opCode4) {
        z80Impl.pc++;
    final int b = z80Impl.b << 8;
    final int c = z80Impl.c;
    final int bc = b + c;
    z80Impl.hl = ( z80Impl.hl + bc);

    final int fAndFlags = z80Impl.f & (Flags.SUBTRACT + Flags.ZERO + Flags.HALFCARRY);
    final int regH = z80Impl.hl & 0xFFFF0000;

    z80Impl.f = (short) fAndFlags;

    if (regH != 0) {
        z80Impl.f = (short) (fAndFlags | (Flags.CARRY));
        z80Impl.hl &= 0xFFFF;
    }

    this.z80Impl.cycles+= 8;
}
}
