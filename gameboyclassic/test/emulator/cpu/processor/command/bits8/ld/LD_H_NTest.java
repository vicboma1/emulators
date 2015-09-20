package emulator.cpu.processor.command.bits8.ld;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 20/09/15.
 */
public class LD_H_NTest extends TestCase {

    private LD_N ld_n;

    private LD_H_N ld_h_n;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();

        z80 = mock(Z80Impl.class);
        ld_n = new LD_N(z80);
        ld_h_n = new LD_H_N(ld_n);
    }

    public void tearDown() throws Exception {
        ld_h_n = null;
        ld_n = null;
    }

    public void testName() throws Exception {
        final int expected = 0x4455;

        z80.hl = 0xFF55;
        ld_h_n.execute(0x22, 0x44, 0x11, 0x99);

        assertTrue("Not same l", z80.hl == expected);
        assertTrue("Not same pc",z80.pc == 2);
        assertTrue("Not same cycles",z80.cycles == 8);
    }
}