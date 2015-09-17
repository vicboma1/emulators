package emulator.cpu.processor.command.bits8.inc;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class INC_BTest extends TestCase {
    private Z80Impl z80;
    private INC_B iNC_B;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        iNC_B =  new INC_B(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testInc_B() throws Exception {
        z80.b = 0;
        z80.pc = 0;
        z80.f = 0x1110;

        iNC_B.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 1);
        assertTrue("Not same f",z80.f == 0x10);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }

    public void testInc_B_0xFF() throws Exception {
        z80.b = 0xFF;
        z80.pc = 0;
        z80.f = 0xAA;

        iNC_B.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 0);
        assertTrue("Not same f",z80.f == 0xA0);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }

    public void testInc_B_0x0F() throws Exception {
        z80.b = 0x0F;
        z80.pc = 0;
        z80.f = 0xAA;

        iNC_B.execute(0, 0, 0, 0);

        assertTrue("Not same b",z80.b == 0x10);
        assertTrue("Not same f",z80.f == 0x20);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }

}

