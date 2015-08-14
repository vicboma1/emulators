package emulator.cpu.processor.processor.command.noOperation;

import cpu.processor.Z80;
import cpu.processor.command.CommandsImpl;

/**
 * Created by vicboma on 13/08/15.
 */
public class NoOperation extends CommandsImpl {

    /**
     * No operation
     * @param z80
     */
    public NoOperation(Z80 z80) {
        super(z80);
    }

    @Override
    public void runAsync(int opCode1, int opCode2, int opCode3, int opCode4) {
        super.NoOp();

    }
}
