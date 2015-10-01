package emulator.cpu.processor.command.bits16.inc;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class INC_BCTest extends TestCase {

    private Z80Impl z80;
    private INC_BC iNC_BC;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        iNC_BC = new INC_BC(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testInc_BC() throws Exception {
        z80.b = 0;
        z80.pc = 0;
        z80.c = 0;

        iNC_BC.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 0);
        assertTrue("Not same c",z80.c == 1 );
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 8);
    }

    public void testInc_BC_OFFSET_C() throws Exception {
        z80.b = 0;
        z80.pc = 0;
        z80.c = INC_BC.OFFSET;

        iNC_BC.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 1);
        assertTrue("Not same c",z80.c == 0 );
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 8);
    }


    public void testInc_BC_OFFSET_B() throws Exception {
        z80.b = INC_BC.OFFSET-1;
        z80.pc = 0;
        z80.c = INC_BC.OFFSET;

        iNC_BC.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 0);
        assertTrue("Not same c",z80.c == 0 );
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 8);
    }
}