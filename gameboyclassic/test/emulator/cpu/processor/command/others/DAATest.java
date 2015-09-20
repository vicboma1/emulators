package emulator.cpu.processor.command.others;

import cpu.processor.register.bits8.Registers8bits;
import cpu.processor.register.bits8.Registers8bitsImpl;
import cpu.processor.register.flag.Flags;
import cpu.processor.z80.Z80Impl;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;

/**
 * Created by vicboma on 20/09/15.
 */
public class DAATest extends TestCase {

    private DAA daa;
    private Registers8bits registers8bits;
    private Z80Impl z80;

    public void setUp() throws Exception {
        super.setUp();

        z80 = mock(Z80Impl.class);
        registers8bits = Registers8bitsImpl.Create();
        z80.registers8bits = registers8bits;
        daa = new DAA(z80);
    }

    public void tearDown() throws Exception {
        daa = null;
    }

    public void test_WithoutSubtractSet_WithoutCarrySet() throws Exception {
        z80.registers8bits.a((short)0x0000);
        z80.f = Flags.NULL;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == 0);
        assertTrue("Not same flag", z80.f == Flags.ZERO);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithoutSubtractSet_WithoutCarrySet_000X() throws Exception {
        final int expected = (0x0810 & 0x00FF);

        z80.registers8bits.a((short)0x080A);
        z80.f = Flags.NULL;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == Flags.NULL);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithoutSubtractSet_WithoutCarrySet_00X0() throws Exception {
        final int expected = (0x0109 & 0x00FF);

        z80.registers8bits.a((short)0x00A9);
        z80.f = Flags.NULL;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == Flags.CARRY);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithoutSubtractSet_WithoutCarrySet_00XX() throws Exception {
        final int expected = (0x0100 & 0x00FF);
        final int expectedFlag = Flags.CARRY | Flags.ZERO;

        z80.registers8bits.a((short)0x009A);
        z80.f = Flags.NULL;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == expectedFlag);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithoutSubtractSet_WithoutCarrySet_00XX_HALFCARRY() throws Exception {
        final int expected = (0x0109 & 0x00FF);

        z80.registers8bits.a((short)0x00A3);
        z80.f = Flags.HALFCARRY;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == Flags.CARRY);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithoutSubtractSet_WithoutCarrySet_000X_HALFCARRY() throws Exception {
        final int expected = (0x0099 & 0x00FF);

        z80.registers8bits.a((short)0x0093);
        z80.f = Flags.HALFCARRY;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == Flags.NULL);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithoutSubtractSet_WithCarrySet_00X0() throws Exception {
        final int expected = (0x0089 & 0x00FF);

        z80.registers8bits.a((short)0x0029);
        z80.f = Flags.CARRY;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == Flags.CARRY);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithoutSubtractSet_WithCarrySet_000X() throws Exception {
        final int expected = (0x0090 & 0x00FF);

        z80.registers8bits.a((short)0x002A);
        z80.f = Flags.CARRY;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == Flags.CARRY);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithoutSubtractSet_WithCarrySet_0000_HALFCARRY() throws Exception {
        final int expected = (0x0099 & 0x00FF);

        z80.registers8bits.a((short)0x0033);
        z80.f = Flags.CARRY | Flags.HALFCARRY;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == Flags.CARRY);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }


    public void test_WithSubtractSet_WithoutCarrySet_00XX_HALFCARRY() throws Exception {
        final int expected = (0x0180 & 0x00FF);

        z80.registers8bits.a((short)0x0086);
        z80.f = Flags.SUBTRACT | Flags.HALFCARRY;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == Flags.SUBTRACT);
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithSubtractSet_WithCarrySet_0XX0() throws Exception {
        final int expected = (0x0119 & 0x00FF);

        z80.registers8bits.a((short)0x0079);
        z80.f = Flags.SUBTRACT | Flags.CARRY;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == (Flags.SUBTRACT | Flags.CARRY));
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

    public void test_WithSubtractSet_WithCarrySet_00XX_HALFCARRY() throws Exception {
        final int expected = (0x0100 & 0x00FF);

        z80.registers8bits.a((short)0x0066);
        z80.f = Flags.SUBTRACT | Flags.CARRY | Flags.HALFCARRY;

        daa.execute(0, 0, 0, 0);

        assertTrue("No same pc", z80.pc == 1);
        assertTrue("Not same a", z80.registers8bits.a() == expected);
        assertTrue("Not same flag", z80.f == (Flags.SUBTRACT | Flags.CARRY | Flags.ZERO));
        assertTrue("Not same Cycles",this.z80.cycles == 4);
    }

}