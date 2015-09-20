package emulator.cpu.processor.command.jump;

import cpu.processor.register.flag.Flags;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 21/09/15.
 */
public class JR_NZ_nnTest extends TestCase {

    private JR_NZ_nn jr_NZ_nn;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();
        z80 = mock(Z80Impl.class);
        jr_NZ_nn = new JR_NZ_nn(z80);
    }

    public void tearDown() throws Exception {
        jr_NZ_nn = null;
        z80 = null;
    }

    public void testjrNzNull() throws Exception {

        z80.f = Flags.NULL;

        jr_NZ_nn.execute(0,0,0,4);

        assertTrue("not same f", z80.f == Flags.NULL);
        assertTrue("not same pc", z80.pc == 6 );
        assertTrue("not same cycles", z80.cycles == 8 );

    }

    public void testjrNz() throws Exception {

        z80.f = Flags.ZERO;

        jr_NZ_nn.execute(0,0,0,8);

        assertTrue("not same f", z80.f == Flags.ZERO);
        assertTrue("not same pc", z80.pc == 2 );
        assertTrue("not same cycles", z80.cycles == 8 );

    }
}