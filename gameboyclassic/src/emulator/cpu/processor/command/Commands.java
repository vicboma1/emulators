package emulator.cpu.processor.command;

/**
 * Created by vicboma on 12/08/15.
 */
public interface Commands {
    void runAsync(int opCode1, int opCode2, int opCode3, int opCode4);
}
