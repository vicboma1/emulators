package emulator.cpu.processor.command._return;

import cpu.processor.register.bit16.Register16bits;
import cpu.processor.register.bits8.Register8bits;
import cpu.processor.register.flag.Flags;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;
import utils.Operation;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by vicboma on 22/09/15.
 */
public class RET_n_NZTest extends TestCase {

    private RET_n ret_n;
    private RET_NZ ret_nz;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();

        this.z80 = mock(Z80Impl.class);
        this.z80.register8Bits = Register8bits.Create();
        this.z80.register16bits = Register16bits.Create();
        this.ret_n = new RET_n(z80);
        this.ret_nz = new RET_NZ(ret_n);
    }

    public void tearDown() throws Exception {
        this.ret_nz = null;
        this.z80 = null;
    }

    public void testIncPC() throws Exception {
        this.z80.register8Bits.f = Flags.ZERO;

        this.ret_nz.execute(0,0,0,0);

        assertTrue("Not same flag", this.z80.register8Bits.f == Flags.ZERO);
        assertTrue("Not same sp", this.z80.register16bits.sp == 0);
        assertTrue("Not same pc", this.z80.register16bits.pc == 1);
        assertTrue("Not same cycles",this.z80.cycles == 0);
    }

    public void testFlagZERO() throws Exception {
        final short completableValue = (short)0xFF00;
        final short completableValueInc = (short)0xFA50;

        final int spValue = 0x24;
        final int increment = 1;

        this.z80.register16bits.sp = spValue;
        this.z80.register8Bits.f = Flags.CARRY;

        when(z80.read(spValue)).thenReturn(CompletableFuture.completedFuture(completableValue));
        when(z80.read(spValue + increment)).thenReturn(CompletableFuture.completedFuture(completableValueInc));

        this.ret_nz.execute(0,0,0,0);

        assertTrue("Not same flag", this.z80.register8Bits.f == Flags.CARRY);
        assertTrue("Not same sp", this.z80.register16bits.sp == spValue + 2);
        assertTrue("Not same pc", this.z80.register16bits.pc == ((Operation.unsign(completableValueInc)) << 8) + Operation.unsign(completableValue));
        assertTrue("Not same cycles",this.z80.cycles == 8);
    }
}
