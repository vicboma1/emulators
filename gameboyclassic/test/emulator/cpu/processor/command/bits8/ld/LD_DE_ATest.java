package emulator.cpu.processor.command.bits8.ld;

import cpu.processor.command.bits8.ld.LD_DE_A;import cpu.processor.memory.MemoryZ80Impl;
import cpu.processor.register.bits8.Registers8bits;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import java.lang.Exception;import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by vicboma on 17/09/15.
 */
public class LD_DE_ATest extends TestCase {

    private LD_DE_A ld_de_a;
    private Z80Impl z80;
    private Registers8bits registers8bits;
    private MemoryZ80Impl memoryZ80;

    public void setUp() throws Exception {
        super.setUp();

        z80 = mock(Z80Impl.class);
        memoryZ80 = mock(MemoryZ80Impl.class);
        registers8bits = mock(Registers8bits.class);
        z80.registers8bits = registers8bits;
        z80.memoryZ80 = memoryZ80;
        ld_de_a = new LD_DE_A(z80);
    }

    public void tearDown() throws Exception {
        ld_de_a = null;
    }

    public void testExecute() throws Exception {
        final int expected = 8209;
        when(z80.registers8bits.a()).thenReturn((short) expected);
        final int value = 0x000F;
        this.z80.d = value;
        this.z80.e = 0x1111;
        final int result = (value << 8) + this.z80.e;

        ld_de_a.execute(0,0,0,0);
        assertTrue("not same a", result == this.registers8bits.a());
        assertTrue("not same pc", this.z80.pc == 1);
        assertTrue("not same cycles", this.z80.cycles == 8);
    }
}
