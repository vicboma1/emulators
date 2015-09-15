package emulator.cpu.processor.command.bits8.dec;

import cpu.processor.command.bits8.dec.DEC_B;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class DEC_BTest extends TestCase {
    private Z80Impl z80;
    private DEC_B dec_b;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        dec_b = new DEC_B(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testDec_00() throws Exception {
        z80.b = 0x00;
        z80.pc = 0;
        z80.f = 0x70;

        dec_b.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 0xFF);
        assertTrue("Not same f",z80.f == 0x70);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }


    public void testDec_01() throws Exception {
        z80.b = 0x01;
        z80.pc = 0;
        z80.f = 0x71;

        dec_b.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 0x00);
        assertTrue("Not same f",z80.f == 0xD0);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }

    public void testDec_10() throws Exception {
        z80.b = 0x10;
        z80.pc = 0;
        z80.f = 0x70;

        dec_b.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 0x0F);
        assertTrue("Not same f",z80.f == 0x70);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }

    public void testDec_11() throws Exception {
        z80.b = 44;
        z80.pc = 0;
        z80.f = 0x77;

        dec_b.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 43);
        assertTrue("Not same f",z80.f == 0x50);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }

    public void testDec_11_2() throws Exception {
        z80.b = 4;
        z80.pc = 0;
        z80.f = 0x77;

        dec_b.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 3);
        assertTrue("Not same f",z80.f == 0x50);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }

}