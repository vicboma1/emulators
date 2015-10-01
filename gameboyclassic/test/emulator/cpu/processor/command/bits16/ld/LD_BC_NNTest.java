package emulator.cpu.processor.command.bits16.ld;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class LD_BC_NNTest extends TestCase {

    private Z80Impl z80;
    private LD_BC_NN ld_bc_NN;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        ld_bc_NN =  new LD_BC_NN(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testLD_BC_nn() throws Exception {
        ld_bc_NN.execute(0, 1, 2, 3);
        assertTrue("Not same b",z80.b == 2);
        assertTrue("Not same c",z80.c == 1 );
        assertTrue("Not same pc",z80.pc == 3);
        assertTrue("Not same cycles",z80.cycles == 12);
    }
}
