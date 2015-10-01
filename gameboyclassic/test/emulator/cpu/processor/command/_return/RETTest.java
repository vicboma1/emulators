package emulator.cpu.processor.command._return;

import cpu.processor.memory.MemoryZ80;
import cpu.processor.register.bit16.Register16bits;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by vicboma on 22/09/15.
 */
public class RETTest extends TestCase {

    private RET ret;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();
        this.z80 = mock(Z80Impl.class);
        this.z80.memoryZ80 = mock(MemoryZ80.class);
        this.z80.register16bits = Register16bits.Create();
        final RET_n ret_n = new RET_n(z80);
        this.ret = new RET(ret_n);
    }

    public void tearDown() throws Exception {
        this.z80 = null;
        this.ret = null;
    }

    public void testExecute() throws Exception {
        final int expected = 0xF923;
        final int valueSP = 0x56;
        final short CompletableFutureValueL = (short) 0x23;
        final short CompletableFutureValueH = (short) 0xF9;

        this.z80.register16bits.pc = 100;
        this.z80.register16bits.sp = valueSP;

        when(this.z80.read(valueSP)).thenReturn(CompletableFuture.completedFuture(CompletableFutureValueL));
        when(this.z80.read(valueSP+1)).thenReturn(CompletableFuture.completedFuture((CompletableFutureValueH)));

        this.ret.execute(0,0,0,0);

        assertTrue("Not same pc", this.z80.register16bits.pc == expected);
        assertTrue("Not same sp", this.z80.register16bits.sp == valueSP + 2);
        assertTrue("Not same cycles", this.z80.cycles == 8);

    }
}
