package emulator.instructions.map;

import emulator.instructions.InstructionCommand;
import emulator.specification.SpecificationsChip8;
import emulator.specification.register.Register;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 11/07/14.
 */
public class Operators {

    private static final int _8xy0_LD_Vx_Vy = 0x0;
    private static final int _8xy1_OR_Vx_Vy = 0x1;
    private static final int _8xy2_AND_Vx_Vy = 0x2;
    private static final int _8xy3_XOR_Vx_Vy = 0x3;
    private static final int _8xy4_ADD_Vx_Vy = 0x4;
    private static final int _8xy5_SUB_Vx_Vy = 0x5;
    private static final int _8xy6_SHR_Vx_Vy = 0x6;
    private static final int _8xy7_SUBN_Vx_Vy = 0x7;
    private static final int _8xyE_SHL_Vx_Vy = 0xE;
    private static final int LAST_REGISTER = 0xF;
    private static final int ZERO = 0x00;
    private static final int ONE = 0x01;
    private static final int MASK8BITS = 0x00FF;
    private static final int MASK12BITS = 0x0FFF;
    private static final int MASK4BITS = 0x000F;
    private static final int MASK_ADD = 0x0F00;

    public static Map<Integer, InstructionCommand> load(final SpecificationsChip8 specificationsChip8) {
        final Register register = specificationsChip8.register();

        return new Hashtable<Integer, InstructionCommand>() {
            {
                put(_8xy0_LD_Vx_Vy, () -> {
                    register.V(register.getOpCode(2), register.V(register.getOpCode(3)));
                });
                put(_8xy1_OR_Vx_Vy, () -> {
                    register.V_OR(register.getOpCode(2), register.getOpCode(3));
                });
                put(_8xy2_AND_Vx_Vy, () -> {
                    register.V_AND(register.getOpCode(2), register.getOpCode(3));
                });
                put(_8xy3_XOR_Vx_Vy, () -> {
                    register.V_XOR(register.getOpCode(2), register.getOpCode(3));
                });
                put(_8xy4_ADD_Vx_Vy, () -> {
                    final int temp = (register.V(register.getOpCode(2)) + register.V(register.getOpCode(3)));
                    register.V(register.getOpCode(2), (short) (temp & MASK8BITS));
                    register.V(LAST_REGISTER, (short) ((temp & MASK_ADD) >> 8));
                });
                put(_8xy5_SUB_Vx_Vy, () -> {
                    final short sign = (short) ((register.V(register.getOpCode(3)) <= register.V(register.getOpCode(2))) ? ONE : ZERO);
                    register.V(register.getOpCode(2), (short) (register.V(register.getOpCode(2)) - register.V(register.getOpCode(3)) & MASK8BITS));
                    register.V(LAST_REGISTER, sign);
                });
                put(_8xy6_SHR_Vx_Vy, () -> {
                    register.V(LAST_REGISTER, (short) (((register.V(register.getOpCode(2)) & MASK4BITS) > 0) ? ONE : ZERO));
                    register.V_SHR(register.getOpCode(2), ONE);
                });
                put(_8xy7_SUBN_Vx_Vy, () -> {
                    final short sign = (short) ((register.V(register.getOpCode(2)) <= register.V(register.getOpCode(3))) ? ONE : ZERO);
                    register.V(register.getOpCode(2), (short) ((register.V(register.getOpCode(3)) - register.V(register.getOpCode(2))) & MASK8BITS));
                    register.V(LAST_REGISTER, sign);
                });
                put(_8xyE_SHL_Vx_Vy, () -> {
                    register.V(LAST_REGISTER, (short) (((register.V(register.getOpCode(2)) >> 7) > 0) ? ONE : ZERO)); //signed
                    register.V(register.getOpCode(2), (short) ((register.V(register.getOpCode(2)) << ONE) & MASK12BITS));
                });
            }
        };
    }


}
