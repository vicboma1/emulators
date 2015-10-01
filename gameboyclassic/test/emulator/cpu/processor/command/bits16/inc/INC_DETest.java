package emulator.cpu.processor.command.bits16.inc;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 17/09/15.
 */
public class INC_DETest extends TestCase {
    private Z80Impl z80;
    private INC_DE iNC_DE;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        iNC_DE = new INC_DE(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testInc_DE() throws Exception {
        z80.d = 0;
        z80.pc = 0;
        z80.e = 0;

        iNC_DE.execute(0, 0, 0, 0);

        assertTrue("Not same b", z80.d == 0);
        assertTrue("Not same c", z80.e == 1);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 8);
    }

    public void testInc_DE_OFFSET_E() throws Exception {
        z80.d = 0;
        z80.pc = 0;
        z80.e = INC_BC.OFFSET;

        iNC_DE.execute(0, 0, 0, 0);

        assertTrue("Not same b", z80.d == 1);
        assertTrue("Not same c", z80.e == 0);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 8);
    }


    public void testInc_DE_OFFSET_D() throws Exception {
        z80.d = INC_BC.OFFSET - 1;
        z80.pc = 0;
        z80.e = INC_BC.OFFSET;

        iNC_DE.execute(0, 0, 0, 0);

        assertTrue("Not same b", z80.d == 0);
        assertTrue("Not same c", z80.e == 0);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 8);
    }
}