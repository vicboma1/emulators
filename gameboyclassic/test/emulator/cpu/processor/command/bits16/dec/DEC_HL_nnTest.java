package emulator.cpu.processor.command.bits16.dec;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 17/09/15.
 */
public class DEC_HL_nnTest extends TestCase {


    private DEC_HL_nn dec_hlNn;
    private Z80Impl z80;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        z80 = mock(Z80Impl.class);
        dec_hlNn = new DEC_HL_nn(z80);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        dec_hlNn = null;
        z80 = null;
    }

    public void testExecute() throws Exception {

        z80.hl = 0x0001;
        dec_hlNn.execute(0,0,0,0);

        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same hl", z80.hl == 0);
        assertTrue("Not same cycles", z80.cycles == 8);

    }

    public void testExecute0() throws Exception {

        z80.hl = 0x0000;
        dec_hlNn.execute(0,0,0,0);

        assertTrue("Not same pc", z80.pc == 1);
        assertTrue("Not same hl", z80.hl == 0xFFFF);
        assertTrue("Not same cycles", z80.cycles == 8);

    }
}
