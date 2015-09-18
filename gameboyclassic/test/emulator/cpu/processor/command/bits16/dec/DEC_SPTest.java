package emulator.cpu.processor.command.bits16.dec;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 17/09/15.
 */
public class DEC_SPTest extends TestCase {

    private DEC_SP dec_sp;
    private Z80Impl z80;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        z80 = mock(Z80Impl.class);
        final DEC_nn dec_nn = new DEC_nn();
        dec_sp = new DEC_SP(z80, dec_nn);
    }
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        dec_sp = null;
        z80 = null;
    }

    public void testExecute() throws Exception {

        z80.sp = 0x0001;

        dec_sp.execute(0,0,0,0);

        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same d", z80.sp == 0);
        assertTrue("Not same cycles", z80.cycles == 8);

    }

    public void testExecute0() throws Exception {

        z80.sp = 0x0000;

        dec_sp.execute(0,0,0,0);

        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same d", z80.sp == 0xFFFF);
        assertTrue("Not same cycles", z80.cycles == 8);

    }
}
