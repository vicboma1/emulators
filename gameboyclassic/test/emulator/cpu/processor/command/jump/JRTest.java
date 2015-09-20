package emulator.cpu.processor.command.jump;

import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 19/09/15.
 */
public class JRTest extends TestCase
{
    private JR jr;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        jr = new JR(z80);
    }

    public void tearDown() throws Exception {
        jr = null;
        z80 = null;
    }
    public void testName() throws Exception {

        jr.execute(0,0,0,2);

        assertTrue("Not same pc", z80.pc == 4);
        assertTrue("Not same cycles", z80.cycles == 8);
    }

    public void testName1() throws Exception {

        jr.execute(0,0,0,4);

        assertTrue("Not same pc", z80.pc == 6);
        assertTrue("Not same cycles", z80.cycles == 8);
    }
}