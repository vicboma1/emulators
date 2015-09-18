package emulator.cpu.processor.command.bits16.dec;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 18/09/15.
 */
public class DEC_BCTest extends TestCase {

    private DEC_BC dec_bc;
    private Z80Impl z80;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        z80 = mock(Z80Impl.class);
        dec_bc = new DEC_BC(z80);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        dec_bc = null;
        z80 = null;
    }

    public void testExecuteC() throws Exception {
        z80.b = 1;
        z80.c = 1;
        dec_bc.execute(0,0,0,0);

        assertTrue("Not same b", z80.b == 1);
        assertTrue("Not same c",z80.c == 0);
        assertTrue("Not same cycles",z80.cycles == 8);

    }

    public void testExecuteCoffset() throws Exception {
        z80.b = 1;
        z80.c = 0;
        dec_bc.execute(0,0,0,0);

        assertTrue("Not same b", z80.b == 0);
        assertTrue("Not same c",z80.c == 0xff);
        assertTrue("Not same cycles",z80.cycles == 8);

    }

    public void testExecuteB() throws Exception {
        z80.b = 1; // xxx
        z80.c = 0xFF;
        dec_bc.execute(0,0,0,0);

        assertTrue("Not same b", z80.b == 1);
        assertTrue("Not same c",z80.c == 0xFE);
        assertTrue("Not same cycles",z80.cycles == 8);

    }

    public void testExecuteBoffset() throws Exception {
        z80.b = 0; // xxx
        z80.c = 0xFF;
        dec_bc.execute(0,0,0,0);

        assertTrue("Not same b", z80.b == 0);
        assertTrue("Not same c",z80.c == 0xFE);
        assertTrue("Not same cycles",z80.cycles == 8);

    }
}