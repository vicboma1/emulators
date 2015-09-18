package emulator.cpu.processor.command.bits8.dec;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 18/09/15.
 */
public class DEC_HTest extends TestCase {


    private Z80Impl z80;
    private DEC_H dec_h;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        dec_h = new DEC_H(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testDec_00() throws Exception {
        z80.hl = 0x0060;   // h = 0x00 & l = 0x60
        z80.pc = 0;
        z80.f = 0x70;

        dec_h.execute(0, 0, 0, 0);

        assertTrue("Not same hl", z80.hl == 0xFF60);  // h = 0xFF & l = 0xNN in this case 0x60 (up declaration)
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }


    public void testDec_01() throws Exception {
        z80.hl = 0x0199;   // h = 0x01 & l = 0x99
        z80.pc = 0;
        z80.f = 0x71;

        dec_h.execute(0, 0, 0, 0);

        assertTrue("Not same hl", z80.hl == 0x0099);  // h = 0x00 & l = 0xNN in this case 0x99 (up declaration)
        assertTrue("Not same f", z80.f == 0xD0);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_10() throws Exception {
        z80.hl = 0x10FF;   // h = 0x10 & l = FF
        z80.pc = 0;
        z80.f = 0x70;

        dec_h.execute(0, 0, 0, 0);

        assertTrue("Not same hl", z80.hl == 0x0FFF);  // h = 0x0F & l = 0xNN in this case 0xFF (up declaration)
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_11() throws Exception {
        z80.hl = 0xFFFF;   // h = 0x10 & l = FF
        z80.pc = 0;
        z80.f = 0x77;

        dec_h.execute(0, 0, 0, 0);

        assertTrue("Not same hl", z80.hl == 0xFEFF);  // h = 0xFE & l = 0xNN in this case 0xFF (up declaration)
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_11_2() throws Exception {
        z80.hl = 0x00FF;   // h = 0x00 & l = FF
        z80.pc = 0;
        z80.f = 0x77;

        dec_h.execute(0, 0, 0, 0);

        assertTrue("Not same hl", z80.hl == 0xFFFF);  // h = 0xFF (overFlow) & l = 0xNN in this case 0xFF (up declaration)
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

}