package emulator.instructions;

import emulator.instructions.map.KeyBoardOp;
import emulator.instructions.map.Operators;
import emulator.instructions.map.ZeroOp;
import emulator.specification.SpecificationsChip8;

import java.util.Map;

/**
 * Created by vicboma on 03/07/14.
 * The original implementation of the Chip-8 language includes 36 different fetch, including math, graphics, and flow control functions.
 * Super Chip-48 added an additional	 10 fetch, for a total of 46
 * All fetch are 2 bytes long and are stored most-significant-byte first. In memory, the first byte of each instruction should be located at an even addresses. If a program includes sprite data, it should be padded so any fetch following it will be properly situated in RAM.
 * This document does not yet contain descriptions of the Super Chip-48 fetch. They are, however, listed below.
 * In these listings, the following variables are used.
 * nnn or addr - A 12-bit value, the lowest 12 bits of the instruction
 * n or nibble - A 4-bit value, the lowest 4 bits of the instruction
 * x - A 4-bit value, the lower 4 bits of the high byte of the instruction
 * y - A 4-bit value, the upper 4 bits of the low byte of the instruction
 * kk or byte - An 8-bit value, the lowest 8 bits of the instruction
 */
public class Instructions {

    private static final String OP_CODE_DID_NOT_MAP = "Op code did not map ";
    private Map<Integer, InstructionCommand> instructions;


    Instructions(Map<Integer, InstructionCommand> instructions) {
        this.instructions = instructions;
    }

    public static Instructions create(final SpecificationsChip8 specificationsChip8) {
        final Map<Integer, InstructionCommand> mapDisposes = ZeroOp.load(specificationsChip8);
        final Map<Integer, InstructionCommand> mapAritmeticOp = Operators.load(specificationsChip8);
        final Map<Integer, InstructionCommand> mapKeyBoardOp = KeyBoardOp.load(specificationsChip8);
        final Map<Integer, InstructionCommand> mapOtherOp = OthersOp.load(specificationsChip8);
        final Map<Integer, InstructionCommand> map = LoadOp.run(specificationsChip8, mapDisposes, mapAritmeticOp, mapKeyBoardOp, mapOtherOp);
        return new Instructions(map);
    }

    public void run(Integer opCode) {
        if (this.instructions.containsKey(opCode))
            this.instructions.get(opCode).execute();
        else
            System.out.println(OP_CODE_DID_NOT_MAP + Integer.toHexString(opCode));
    }
}
