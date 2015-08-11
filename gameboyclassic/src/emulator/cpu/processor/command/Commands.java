package emulator.cpu.processor.command;

import cpu.processor.Z80;

/**
 * Created by vicboma on 12/08/15.
 */
public interface Commands {
    void runAsync(int opCode1, int opCode2, int opCode3, int opCode4, Z80 z80);
}
