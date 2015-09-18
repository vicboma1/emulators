package emulator.cpu.processor.command.bits8.dec;

import cpu.processor.register.bits8.Registers8bits;
import cpu.processor.register.bits8.Registers8bitsImpl;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 18/09/15.
 */
public class DEC_ATest extends TestCase {

    private DEC_A dec_a;
    private Z80Impl z80;
    private Registers8bits registers8bits;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        dec_a = new DEC_A(z80);
        registers8bits = Registers8bitsImpl.Create();
        z80.registers8bits = registers8bits;
    }

    public void tearDown() throws Exception {
        registers8bits = null;
        dec_a = null;
        z80 = null;
    }

    public void testDec_A00() throws Exception {
        z80.registers8bits.a((short) 0x00);
        z80.pc = 0;
        z80.f = 0x70;

        dec_a.execute(0, 0, 0, 0);

        assertTrue("Not same register 8 bits a", z80.registers8bits.a() == 0xFF);
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }


    public void testDec_A01() throws Exception {
        z80.registers8bits.a((short) 0x01);
        z80.pc = 0;
        z80.f = 0x71;

        dec_a.execute(0, 0, 0, 0);

        assertTrue("Not same register 8 bits a", z80.registers8bits.a() == 0x00);
        assertTrue("Not same f", z80.f == 0xD0);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_A10() throws Exception {
        z80.registers8bits.a((short) 0x10);
        z80.pc = 0;
        z80.f = 0x70;

        dec_a.execute(0, 0, 0, 0);

        assertTrue("Not same register 8 bits a", z80.registers8bits.a() == 0x0F);
        assertTrue("Not same f", z80.f == 0x70);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_A11() throws Exception {
        z80.registers8bits.a((short) 44);
        z80.pc = 0;
        z80.f = 0x77;

        dec_a.execute(0, 0, 0, 0);

        assertTrue("Not same register 8 bits a", z80.registers8bits.a() == 43);
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testDec_11_2() throws Exception {
        z80.registers8bits.a((short) 4);
        z80.pc = 0;
        z80.f = 0x77;

        dec_a.execute(0, 0, 0, 0);

        assertTrue("Not same register 8 bits a", z80.registers8bits.a() == 3);
        assertTrue("Not same f", z80.f == 0x50);
        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }
}