package emulator.cpu.processor.command.bits16.add;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 21/09/15.
 */
public class ADD_HL_DETest extends TestCase {
    private ADD_HL_DE add_hl_de;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);

        add_hl_de = new ADD_HL_DE(new ADD_HL_nn(z80));
    }

    public void tearDown() throws Exception {
        add_hl_de = null;
    }

    public void testRunAsync() throws Exception {
        z80.d = 16;
        z80.e = 44;
        z80.hl = 210;
        z80.f = 15;

        add_hl_de.execute(0,0,0,0);

        assertTrue("Not same d", z80.d == 16);
        assertTrue("Not same e",z80.e == 44 );
        assertTrue("Not same pc",z80.pc == 1);
        assertTrue("Not same hl", z80.hl == 4350);
        assertTrue("Not same f", z80.f == 0);
        assertTrue("Not same cycles",z80.cycles == 8);
    }

}