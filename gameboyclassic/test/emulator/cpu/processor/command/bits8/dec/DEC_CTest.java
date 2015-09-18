package emulator.cpu.processor.command.bits8.dec;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 15/09/15.
 */
public class DEC_CTest extends TestCase {

    private Z80Impl z80;
    private DEC_C dec_c;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        dec_c = new DEC_C(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testDec_00() throws Exception {
        z80.c = 0x00;
        z80.pc = 0;
        z80.f = 0x70;

        dec_c.execute(0, 0, 0, 0);

        assertTrue("Not same c", z80.c == 0xFF);
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }


    public void testDec_01() throws Exception {
        z80.c= 0x01;
        z80.pc = 0;
        z80.f = 0x71;

        dec_c.execute(0, 0, 0, 0);

        assertTrue("Not same c", z80.c == 0x00);
        assertTrue("Not same f", z80.f == 0xD0);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_10() throws Exception {
        z80.c = 0x10;
        z80.pc = 0;
        z80.f = 0x70;

        dec_c.execute(0, 0, 0, 0);

        assertTrue("Not same c",  z80.c == 0x0F);
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_11() throws Exception {
        z80.c = 44;
        z80.pc = 0;
        z80.f = 0x77;

        dec_c.execute(0, 0, 0, 0);

        assertTrue("Not same c",  z80.c == 43);
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_11_2() throws Exception {
        z80.c = 4;
        z80.pc = 0;
        z80.f = 0x77;

        dec_c.execute(0, 0, 0, 0);

        assertTrue("Not same c", z80.c == 3);
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }
}