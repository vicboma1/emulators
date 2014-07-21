package emulator.instructions.map;

import emulator.instructions.InstructionCommand;
import emulator.specification.SpecificationsChip8;
import emulator.specification.input.KeyBoard;
import emulator.specification.input.KeyBoardStatus;
import emulator.specification.register.Register;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 11/07/14.
 */
public class KeyBoardOp {

    private static final int Ex9E_SKP_Vx = 0x9E;
    private static final int ExA1_SKNP_Vx = 0xA1;

    public static Map<Integer, InstructionCommand> load(SpecificationsChip8 specificationsChip8) {

        final Register register = specificationsChip8.register();
        final KeyBoard keyBoard = specificationsChip8.keyBoard();
        return new Hashtable<Integer, InstructionCommand>() {
            {
                put(Ex9E_SKP_Vx, () -> {
                    if (keyBoard.get(KeyBoardStatus.unMap[register.V(register.getOpCode(2))]))
                        incremetIP();
                });
                put(ExA1_SKNP_Vx, () -> {
                    if (!keyBoard.get(KeyBoardStatus.unMap[register.V(register.getOpCode(2))]))
                        incremetIP();
                });
            }

            private void incremetIP() {
                register.IP(register.IP() + 2);
            }
        };
    }
}
