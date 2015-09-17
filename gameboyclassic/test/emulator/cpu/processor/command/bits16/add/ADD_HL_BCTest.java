package emulator.cpu.processor.command.bits16.add;

import emulator.cpu.processor.command.bits16.ADD_HL_BC;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

public class ADD_HL_BCTest extends TestCase {

    private ADD_HL_BC add_hl_bc;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        add_hl_bc = new ADD_HL_BC(z80);
    }

    public void tearDown() throws Exception {
        add_hl_bc = null;
    }

    public void testRunAsync() throws Exception {
        z80.pc = 35440;
        z80.b = 51;
        z80.c = 30;

        z80.hl = 210;
        z80.f = 15;

        z80.cycles = 0;

        add_hl_bc.execute(0,0,0,0);

        assertTrue("Not same b", z80.b == 51);
        assertTrue("Not same c",z80.c == 30 );
        assertTrue("Not same pc",z80.pc == 35441);
        assertTrue("Not same hl", z80.hl == 13296);
        assertTrue("Not same f", z80.f == 0);
        assertTrue("Not same cycles",z80.cycles == 8);
    }

}