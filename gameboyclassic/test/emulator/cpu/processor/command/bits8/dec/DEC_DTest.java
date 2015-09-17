package emulator.cpu.processor.command.bits8.dec;

import cpu.processor.command.bits8.dec.DEC_D;import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import java.lang.Exception;import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 17/09/15.
 */
public class DEC_DTest extends TestCase {
    private DEC_D dec_d;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        dec_d = new DEC_D(z80);
    }

    public void tearDown() throws Exception {

    }

    public void testDec_C00() throws Exception {
        z80.d = 0x00;
        z80.pc = 0;
        z80.f = 0x70;

        dec_d.execute(0, 0, 0, 0);

        assertTrue("Not same d", z80.d == 0xFF);
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }


    public void testDec_C01() throws Exception {
        z80.d = 0x01;
        z80.pc = 0;
        z80.f = 0x71;

        dec_d.execute(0, 0, 0, 0);

        assertTrue("Not same d", z80.d == 0x00);
        assertTrue("Not same f", z80.f == 0xD0);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_C10() throws Exception {
        z80.d = 0x10;
        z80.pc = 0;
        z80.f = 0x70;

        dec_d.execute(0, 0, 0, 0);

        assertTrue("Not same d", z80.d == 0x0F);
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_C11() throws Exception {
        z80.d = 44;
        z80.pc = 0;
        z80.f = 0x77;

        dec_d.execute(0, 0, 0, 0);

        assertTrue("Not same d", z80.d == 43);
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_11_2() throws Exception {
        z80.d = 4;
        z80.pc = 0;
        z80.f = 0x77;

        dec_d.execute(0, 0, 0, 0);

        assertTrue("Not same d", z80.d == 3);
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }
}