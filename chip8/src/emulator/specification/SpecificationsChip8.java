package emulator.specification;

import emulator.specification.graphics.Video;
import emulator.specification.input.KeyBoard;
import emulator.specification.memory.Memory;
import emulator.specification.register.Register;
import emulator.specification.timer.Timer;

/**
 * Created by vicboma on 10/07/14.
 */
public interface SpecificationsChip8 {
    Memory memory();

    Register register();

    Video video();

    KeyBoard keyBoard();

    Timer timer();
}
