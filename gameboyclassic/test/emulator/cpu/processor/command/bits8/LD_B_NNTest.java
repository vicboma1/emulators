package emulator.cpu.processor.command.bits8;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class LD_B_NNTest extends TestCase {

    private Z80Impl z80;
    private LD_B_NN ld_b_NN;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        ld_b_NN = new LD_B_NN(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testld_b_n_2() throws Exception {
        ld_b_NN.execute(1, 2, 2, 2);

        assertTrue("Not same b", z80.b == 2);
        assertTrue("Not same pc", z80.pc == 2);
        assertTrue("Not same cycles", z80.cycles == 8);
    }

    public void testld_b_3() throws Exception {
        ld_b_NN.execute(3, 1, 3, 0);

        assertTrue("Not same b", z80.b == 1);
        assertTrue("Not same pc", z80.pc == 2);
        assertTrue("Not same cycles", z80.cycles == 8);
    }

}