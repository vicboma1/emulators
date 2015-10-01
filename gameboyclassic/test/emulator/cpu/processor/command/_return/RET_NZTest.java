package emulator.cpu.processor.command._return;

import cpu.processor.memory.MemoryZ80;
import cpu.processor.register.bit16.Register16bits;
import cpu.processor.register.bits8.Register8bits;
import cpu.processor.register.flag.Flags;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by vicboma on 22/09/15.
 */
public class RET_NZTest extends TestCase {

    private RET_NZ ret_nz;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();
        this.z80 = mock(Z80Impl.class);
        this.z80.memoryZ80 = mock(MemoryZ80.class);
        this.z80.register8Bits = Register8bits.Create();
        this.z80.register16bits = Register16bits.Create();

        final RET_n ret_n = new RET_n(z80);
        this.ret_nz = new RET_NZ(ret_n);
    }

    public void tearDown() throws Exception {
        this.z80 = null;
        this.ret_nz = null;
    }

    public void testFlagZero() throws Exception {
        z80.register8Bits.f = Flags.ZERO;

        this.ret_nz.execute(0, 0,0,0);

        assertTrue("Not same pc", this.z80.register16bits.pc == 1);
        assertTrue("Not same z80.registers8bits.f", this.z80.register8Bits.f == Flags.ZERO);
        assertTrue("Not same cycles", this.z80.cycles == 0);
    }

    public void testFlagNull() throws Exception {
        final int expected = 0xF923;
        final int valueSP = 0x56;
        final short CompletableFutureValueL = (short) 0x23;
        final short CompletableFutureValueH = (short) 0xF9;

        z80.register8Bits.f = Flags.NULL;
        this.z80.register16bits.pc = 100;
        this.z80.register16bits.sp = valueSP;

        when(this.z80.read(valueSP)).thenReturn(CompletableFuture.completedFuture(CompletableFutureValueL));
        when(this.z80.read(valueSP+1)).thenReturn(CompletableFuture.completedFuture((CompletableFutureValueH)));

        this.ret_nz.execute(0, 0, 0, 0);

        assertTrue("Not same pc", this.z80.register16bits.pc == expected);
        assertTrue("Not same z80.registers8bits.f", z80.register8Bits.f == Flags.NULL);
        assertTrue("Not same sp", this.z80.register16bits.sp == valueSP + 2);
        assertTrue("Not same cycles", this.z80.cycles == 8);
    }
}