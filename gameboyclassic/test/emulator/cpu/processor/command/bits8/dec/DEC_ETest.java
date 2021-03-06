package emulator.cpu.processor.command.bits8.dec;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 18/09/15.
 */
public class DEC_ETest extends TestCase {

    private Z80Impl z80;
    private DEC_E dec_e;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        dec_e = new DEC_E(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testDec_00() throws Exception {
        z80.e = 0x00;
        z80.pc = 0;
        z80.f = 0x70;

        dec_e.execute(0, 0, 0, 0);

        assertTrue("Not same e", z80.e == 0xFF);
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }


    public void testDec_01() throws Exception {
        z80.e= 0x01;
        z80.pc = 0;
        z80.f = 0x71;

        dec_e.execute(0, 0, 0, 0);

        assertTrue("Not same e", z80.e == 0x00);
        assertTrue("Not same f", z80.f == 0xD0);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_10() throws Exception {
        z80.e = 0x10;
        z80.pc = 0;
        z80.f = 0x70;

        dec_e.execute(0, 0, 0, 0);

        assertTrue("Not same e",  z80.e == 0x0F);
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_11() throws Exception {
        z80.e = 44;
        z80.pc = 0;
        z80.f = 0x77;

        dec_e.execute(0, 0, 0, 0);

        assertTrue("Not same e",  z80.e == 43);
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_11_2() throws Exception {
        z80.e = 4;
        z80.pc = 0;
        z80.f = 0x77;

        dec_e.execute(0, 0, 0, 0);

        assertTrue("Not same e", z80.e == 3);
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }
}