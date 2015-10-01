package emulator.cpu.processor.command.bits16.dec;

import cpu.processor.command.CommandsImpl;
import cpu.processor.z80.Z80Impl;

/**
 * Created by vicboma on 12/08/15.
 */
public class DEC_BC extends CommandsImpl {

    public static final int OP_CODE_0X0B = 0x0B;

    /**
     * Increment register nn.
     */
    public DEC_BC(Z80Impl z80Impl) {
        super(z80Impl);
    }

    @Override
    public void execute(int opCode1, int opCode2, int opCode3, int opCode4) {
        this.z80Impl.pc++;
        decrementC();
        final int offsetC = this.z80Impl.c & 0xFF00;
        if (offsetC != 0) {
            this.z80Impl.c = 0xFF;
            decrementB();
            final int offsetB = this.z80Impl.b & 0xFF00;
            if (offsetB != 0) {
                this.z80Impl.b = 0xFF;
            }
        }
        this.z80Impl.cycles+=8;
    }

    private void decrementB() {
        this.z80Impl.b--;
    }

    private void decrementC() {
        this.z80Impl.c--;
    }
}