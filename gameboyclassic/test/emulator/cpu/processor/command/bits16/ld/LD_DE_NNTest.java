package emulator.cpu.processor.command.bits16.ld;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 17/09/15.
 */
public class LD_DE_NNTest extends TestCase {
    private Z80Impl z80;
    private LD_DE_NN ld_de_NN;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        ld_de_NN = new LD_DE_NN(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testLD_DE_nn() throws Exception {
        ld_de_NN.execute(0, 1, 2, 3);
        assertTrue("Not same b", z80.d == 2);
        assertTrue("Not same e", z80.e == 1);
        assertTrue("Not same pc", z80.pc == 3);
        assertTrue("Not same cycles", z80.cycles == 12);
    }
}