package emulator.instructions.map;

import emulator.instructions.InstructionCommand;
import emulator.specification.SpecificationsChip8;
import emulator.specification.graphics.Display;
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
public class ZeroOp {

    private static final int _00E0_CLS = 0x0E;
    private static final int _00EE_RET = 0xEE;

    public static Map<Integer, InstructionCommand> load(SpecificationsChip8 specificationsChip8) {
        final Register register = specificationsChip8.register();
        final Video video = specificationsChip8.video();
        final Memory memory = specificationsChip8.memory();

        return new Hashtable<Integer, InstructionCommand>() {
            {
                put(_00E0_CLS, () -> {
                    IntStream stream = IntStream.range(Utils.ZERO, Video.WIDTH * Video.HEIGHT);
                    stream.sequential().forEach(x -> {
                        video.display().matrix(x, Display.BACKGROUND);
                    });
                    video.refresh();
                });
                put(_00EE_RET, () -> {
                    register.IP(memory.ShiftL(register.SP(), 8));
                    incrementSP(register);
                    register.IP(register.IP() + memory.get(register.SP()));
                    incrementSP(register);
                });
            }

            private void incrementSP(Register register) {
                register.SP(register.SP() + 1);
            }
        };
    }
}
