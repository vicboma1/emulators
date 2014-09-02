package emulator.instructions.map;

import emulator.instructions.InstructionCommand;
import emulator.specification.SpecificationsChip8;
import emulator.specification.graphics.Display;
import emulator.specification.graphics.Render;
import emulator.specification.graphics.Video;
import emulator.specification.memory.Memory;
import emulator.specification.register.Register;
import emulator.utils.Utils;

import java.util.Hashtable;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by vicboma on 11/07/14.
 */
public class LoadOp {

    private static final int _00XX = 0x0;
    private static final int _1NNN_JP_addr = 0x1;
    private static final int _2NNN_CALL_addr = 0x2;
    private static final int _3XKK_SE_Vx_BYTE = 0x3;
    private static final int _4XKK_SNE_Vx_BYTE = 0x4;
    private static final int _5XKK_SE_Vx_Vy = 0x5;
    private static final int _6XKK_LD_Vx_byte = 0x6;
    private static final int _6XKK_ADD_Vx_BYTE = 0x7;
    private static final int _8XYZ = 0x8;
    private static final int _9XY0_SNE_Vx_Vy = 0x9;
    private static final int _ANNN_LD_I_ADDR = 0xA;
    private static final int _BNNN_JP_V0_ADDR = 0xB;
    private static final int _CXKK_RND_Vx_byte = 0xC;
    private static final int _DXYN_DRW_Vx_Vy_NIBBLE = 0xD;
    private static final int _EXZZ = 0xE;
    private static final int _FXZZ = 0xF;


    public static Map<Integer, InstructionCommand> run(final SpecificationsChip8 specificationsChip8,
                                                       final Map<Integer, InstructionCommand> mapDispose,
                                                       final Map<Integer, InstructionCommand> mapAritmeticOp,
                                                       final Map<Integer, InstructionCommand> mapKeyBoardOp,
                                                       final Map<Integer, InstructionCommand> mapOtherOp) {
        final Register register = specificationsChip8.register();
        final Video video = specificationsChip8.video();
        final Memory memory = specificationsChip8.memory();

        return new Hashtable<Integer, InstructionCommand>() {
            {
                put(_00XX, () -> {
                    final int instruction = instructionMask8Bits(register.opCode());
                    if (mapDispose.containsKey(instruction)) {
                        mapDispose.get(instruction).execute();
                        incremetIP();
                    }
                });
                put(_1NNN_JP_addr, () -> {
                    jump();
                });
                put(_2NNN_CALL_addr, () -> {
                    decrementSP();
                    memory.set(register.SP(), (short) (register.IP() & mask8bits()));
                    decrementSP();
                    memory.set(register.SP(), (short) (register.IP() >> 8));
                    jump();
                });
                put(_3XKK_SE_Vx_BYTE, () -> {
                    if (register.V(register.getOpCode(2)) == instructionMask8Bits(register.opCode()))
                        incremetIP();

                    incremetIP();
                });
                put(_4XKK_SNE_Vx_BYTE, () -> {
                    if (register.V(register.getOpCode(2)) != instructionMask8Bits(register.opCode()))
                        incremetIP();

                    incremetIP();
                });
                put(_5XKK_SE_Vx_Vy, () -> {
                    if (register.V(register.getOpCode(2)) == register.V(register.getOpCode(3)))
                        incremetIP();

                    incremetIP();
                });
                put(_6XKK_LD_Vx_byte, () -> {
                    register.V(register.getOpCode(2), instructionMask8Bits(register.opCode()));
                    incremetIP();
                });
                put(_6XKK_ADD_Vx_BYTE, () -> {
                    register.V(register.getOpCode(2), (short) ((register.V(register.getOpCode(2)) + register.opCode() & mask8bits()) & mask8bits()));
                    incremetIP();
                });
                put(_8XYZ, () -> {
                    final int opCode4 = new Integer(register.getOpCode(4));
                    if (mapAritmeticOp.containsKey(opCode4)) {
                        mapAritmeticOp.get(opCode4).execute();
                        incremetIP();
                    }
                });
                put(_9XY0_SNE_Vx_Vy, () -> {
                    if (register.V(register.getOpCode(2)) != register.V(register.getOpCode(3)))
                        incremetIP();

                    incremetIP();
                });
                put(_ANNN_LD_I_ADDR, () -> {
                    register.I(register.opCode() & mask12bits());
                    incremetIP();
                });
                put(_BNNN_JP_V0_ADDR
                        , () -> {
                    register.IP(((register.opCode() & mask12bits()) + register.V(0)) & mask12bits());
                });
                put(_CXKK_RND_Vx_byte, () -> {
                    register.V(register.getOpCode(2), (short) (((int) (Math.random() * mask16bits()) & mask8bits()) & (register.opCode() & mask8bits())));
                    incremetIP();
                });
                put(_DXYN_DRW_Vx_Vy_NIBBLE, () -> {
                    final int visibleHeight = Display.HEIGHT / 10;
                    final int visibleWidth = Display.WIDTH / 10;
                    final int posX = register.V(register.getOpCode(2)) % visibleWidth;
                    final int posY = register.V(register.getOpCode(3)) % visibleHeight;

                    final IntStream stream = IntStream.range(Utils.ZERO, register.getOpCode(4));
                    if (register.getOpCode(4) != Utils.ZERO) {
                        stream.sequential()
                                .filter(x -> posY + x < visibleHeight)
                                .forEach(x -> {
                                    final short[] pixelRow = toBinshort(memory.get(register.I() + x));
                                    final boolean collision = Render.create(video, pixelRow, posX, posY + x).binaryMatrix();
                                    register.V(0xF, (short) ((collision) ? 0x01 : Utils.ZERO));
                                });

                    }
                    video.refresh();
                    incremetIP();
                });
                put(_EXZZ, () -> {
                    final int instruction8bitsLower = instructionMask8Bits(register.opCode());
                    if (mapKeyBoardOp.containsKey(instruction8bitsLower)) {
                        mapKeyBoardOp.get(instruction8bitsLower).execute();
                        incremetIP();
                    }
                });
                put(_FXZZ, () -> {
                    final int instruction8bitsLower = instructionMask8Bits(register.opCode());
                    if (mapOtherOp.containsKey(instruction8bitsLower)) {
                        mapOtherOp.get(instruction8bitsLower).execute();
                        incremetIP();
                    }
                });
            }

            private void decrementSP() {
                register.SP(register.SP() - 1);
            }

            private int mask16bits() {
                return 0xFFFF;
            }

            private int mask8bits() {
                return 0x00FF;
            }

            private int mask12bits() {
                return 0x0FFF;
            }

            private void jump() {
                register.IP(register.opCode() & mask12bits());
            }

            private void incremetIP() {
                register.IP(register.IP() + 2);
            }

            private short instructionMask8Bits(int number) {
                return (short) (number & mask8bits());
            }

            private short[] toBinshort(short car) {
                short[] binShort = new short[8];
                final int mask = 0x80;
                final IntStream stream = IntStream.range((Utils.ZERO), binShort.length);
                stream.sequential().forEach(x -> {
                    binShort[x] = (short) (((car) & (mask >> x)) > Utils.ZERO ? 0x01 : Utils.ZERO);
                });

                return binShort;
            }
        };
    }
}
