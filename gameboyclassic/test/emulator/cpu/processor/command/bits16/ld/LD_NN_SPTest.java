package emulator.cpu.processor.command.bits16.ld;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LD_NN_SPTest extends TestCase {

    private Z80Impl z80;
    private LD_NN_SP ld_nn_sp;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        ld_nn_sp = mock(LD_NN_SP.class);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void test() throws Exception {
        z80.pc = 3;
        z80.cycles = 20;

        ld_nn_sp.execute(0, 0, 0, 0);
        verify(ld_nn_sp).execute(0, 0, 0, 0);

        assertTrue("Not same pc",z80.pc == 3);
        assertTrue("Not same cycles",z80.cycles == 20F);
    }

}
