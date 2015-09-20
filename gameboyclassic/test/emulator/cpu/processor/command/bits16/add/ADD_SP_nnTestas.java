package emulator.cpu.processor.command.bits16.add;

import cpu.processor.register.flag.Flags;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 18/09/15.
 */
public class ADD_SP_nnTestas extends TestCase {

    private Z80Impl z80;
    private ADD_SP_nn add_sp_nn;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        add_sp_nn = new ADD_SP_nn(z80);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        z80= null;
        add_sp_nn = null;
    }

    public void testName() throws Exception {

        z80.sp = 0xFFFF;
        add_sp_nn.execute(0,0,0,4);

        assertTrue("not same f", z80.f == Flags.CARRY);
        assertTrue("not same sp", z80.sp == 0x0003);
        assertTrue("not same pc", z80.pc == 2);
        assertTrue("not same cycles", z80.cycles == 16);
    }

    public void testName2() throws Exception {

        z80.sp = 0x0065;
        add_sp_nn.execute(0,0,0,4);

        assertTrue("not same f", z80.f == (z80.f & (Flags.SUBTRACT + Flags.ZERO + Flags.HALFCARRY)));
        assertTrue("not same sp", z80.sp == 0x0069);
        assertTrue("not same pc", z80.pc == 2);
        assertTrue("not same cycles", z80.cycles == 16);
    }
}