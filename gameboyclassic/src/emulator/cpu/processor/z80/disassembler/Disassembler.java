package emulator.cpu.processor.z80.disassembler;

/**
 * Created by vicboma on 05/10/15.
 */
public interface Disassembler {
    void execute(StringBuilder instruction, Short hi, Short lo, boolean isDecimal, boolean isHexadecimal);
}
