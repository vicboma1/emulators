package emulator.instructions.map;

import emulator.instructions.InstructionCommand;
import emulator.specification.SpecificationsChip8;
import emulator.specification.input.KeyBoard;
import emulator.specification.memory.Memory;
import emulator.specification.register.Register;
import emulator.specification.timer.Timer;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 11/07/14.
 */
public class OthersOp {

    private static final int _Fx07_LD_Vx_DT = 0x07;
    private static final int _Fx0A_LD_Vx_K = 0x0A;
    private static final int _Fx15_LD_DT_Vx = 0x15;
    private static final int _Fx18_LD_ST_Vx = 0x18;
    private static final int _Fx1E_ADD_I_Vx = 0x1E;
    private static final int _Fx29_LD_F_Vx = 0x29;
    private static final int _Fx33_LD_B_Vx = 0x33;
    private static final int _Fx55_LD_I_Vx = 0x55;
    private static final int _Fx65_LD_Vx_I = 0x65;
    private static final int MASK12BITS = 0x0FFF;
    private static final int MASK4BITS = 0x0F;

    public static Map<Integer, InstructionCommand> load(final SpecificationsChip8 specificationsChip8) {
        final Register register = specificationsChip8.register();
        final Memory memory = specificationsChip8.memory();
        final KeyBoard keyBoard = specificationsChip8.keyBoard();
        final Timer timer = specificationsChip8.timer();

        return new Hashtable<Integer, InstructionCommand>() {
            {
                put(_Fx07_LD_Vx_DT, () -> {
                    register.V(register.getOpCode(2), timer.delay());
                });
                put(_Fx0A_LD_Vx_K, () -> {
                    if (keyBoard.status().isOtherKeyPressed)
                        register.V(register.getOpCode(2), keyBoard.status().actualKeyPressed);
                });
                put(_Fx15_LD_DT_Vx, () -> {
                    timer.delay(register.V(register.getOpCode(2)));
                });
                put(_Fx18_LD_ST_Vx, () -> {
                    timer.sound(register.V(register.getOpCode(2)));
                });
                put(_Fx1E_ADD_I_Vx, () -> {
                    register.I((register.I() + register.V(register.getOpCode(2))) & MASK12BITS);
                });
                put(_Fx29_LD_F_Vx, () -> {
                    register.I((register.V(register.getOpCode(2)) & MASK4BITS) * 5);
                });
                put(_Fx33_LD_B_Vx, () -> {
                    short i, j;
                    i = register.V(register.getOpCode(2));
                    for (j = 0; i >= 100; i -= 100) j++;
                    memory.set(register.I(), j);
                    for (j = 0; i >= 10; i -= 10) j++;
                    memory.set(register.I() + 1, j);
                    memory.set(register.I() + 2, i);
                });
                //
                put(_Fx55_LD_I_Vx, () -> {
                    for (int i = 0, j = (register.opCode() >> 8) & 0x0F; i <= j; i++) {
                        memory.set(register.I() + i, register.V(i));
                    }
                });
                put(_Fx65_LD_Vx_I, () -> {
                    for (int i = 0, j = (register.opCode() >> 8) & 0x0F; i <= j; i++) {
                        register.V(i, memory.get(register.I() + i));
                    }
                });
            }
        };
    }
}
