package emulator.cpu.processor.command.bits8.inc;

import cpu.processor.command.bits8.inc.INC_D;import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import java.lang.Exception;import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 17/09/15.
 */
public class INC_DTest extends TestCase {
    private Z80Impl z80;
    private INC_D inc_D;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        inc_D =  new INC_D(z80);
    }

    public void tearDown() throws Exception {
        z80 = null;
    }

    public void testInc_D() throws Exception {
        z80.d = 0;
        z80.pc = 0;
        z80.f = 0x1110;

        inc_D.execute(0, 0, 0, 0);

        assertTrue("Not same d",z80.d == 1);
        assertTrue("Not same f",z80.f == 0x10);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles", z80.cycles == 4);
    }

    public void testInc_D_0xFF() throws Exception {
        z80.d = 0xFF;
        z80.pc = 0;
        z80.f = 0xAA;

        inc_D.execute(0, 0, 0, 0);

        assertTrue("Not same d",z80.d == 0);
        assertTrue("Not same f",z80.f == 0xA0);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }

    public void testInc_D_0x0F() throws Exception {
        z80.d = 0x0F;
        z80.pc = 0;
        z80.f = 0xAA;

        inc_D.execute(0, 0, 0, 0);

        assertTrue("Not same d",z80.d == 0x10);
        assertTrue("Not same f",z80.f == 0x20);
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same cycles",z80.cycles == 4);
    }
}