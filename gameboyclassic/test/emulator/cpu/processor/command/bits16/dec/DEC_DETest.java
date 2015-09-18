package emulator.cpu.processor.command.bits16.dec;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 18/09/15.
 */
public class DEC_DETest extends TestCase {

    private DEC_DE dec_de;
    private Z80Impl z80;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        z80 = mock(Z80Impl.class);
        dec_de = new DEC_DE(z80);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        dec_de = null;
        z80 = null;
    }

    public void testExecuteC() throws Exception {
        z80.d = 1;
        z80.e = 1;
        dec_de.execute(0,0,0,0);

        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same d", z80.d == 1);
        assertTrue("Not same e",z80.e == 0);
        assertTrue("Not same cycles",z80.cycles == 8);

    }

    public void testExecuteCoffset() throws Exception {
        z80.d = 1;
        z80.e = 0;
        dec_de.execute(0, 0, 0, 0);

        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same d", z80.d == 0);
        assertTrue("Not same e", z80.e == 0xff);
        assertTrue("Not same cycles",z80.cycles == 8);

    }

    public void testExecuteB() throws Exception {
        z80.d = 1;
        z80.e = 0xFF;
        dec_de.execute(0,0,0,0);

        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same d", z80.d == 1);
        assertTrue("Not same e", z80.e == 0xfe);
        assertTrue("Not same cycles",z80.cycles == 8);

    }

    public void testExecuteBoffset() throws Exception {
        z80.d = 0;
        z80.e = 0xFF;
        dec_de.execute(0,0,0,0);

        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same d", z80.d == 0);
        assertTrue("Not same e", z80.e == 0xfe);
        assertTrue("Not same cycles",z80.cycles == 8);

    }
}