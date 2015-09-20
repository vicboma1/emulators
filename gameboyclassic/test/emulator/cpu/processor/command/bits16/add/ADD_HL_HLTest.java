package emulator.cpu.processor.command.bits16.add;

import cpu.processor.register.flag.Flags;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 20/09/15.
 */
public class ADD_HL_HLTest extends TestCase {

    private Z80Impl z80;
    private ADD_HL_HL add_hl_hl;
    private ADD_HL_nn add_hl_nn;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        add_hl_nn = new ADD_HL_nn(z80);
        add_hl_hl = new ADD_HL_HL(add_hl_nn);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        z80= null;
        add_hl_hl = null;
        add_hl_nn = null;
    }

    public void testName() throws Exception {
        final int expectedHL = 57374;
        z80.hl = 0xf00f;

        add_hl_hl.execute(0,0,0,0);

        assertTrue("not same f", z80.f == Flags.CARRY);
        assertTrue("not same hl", z80.hl == expectedHL);
        assertTrue("not same pc", z80.pc == 1);
        assertTrue("not same cycles", z80.cycles == 8);

    }

    public void testName2() throws Exception {
        final int expectedHL = 0;
        z80.hl = 0;

        add_hl_hl.execute(0,0,0,0);

        assertTrue("not same f", z80.f == (z80.f & (Flags.SUBTRACT + Flags.ZERO + Flags.HALFCARRY)));
        assertTrue("not same hl", z80.hl == expectedHL);
        assertTrue("not same pc", z80.pc == 1);
        assertTrue("not same cycles", z80.cycles == 8);

    }
}