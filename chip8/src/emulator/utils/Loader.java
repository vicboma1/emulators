package emulator.utils;

import emulator.specification.memory.Memory;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by vicboma on 08/07/14.
 */
public class Loader {

    private static final String ROM_SIZE = "Rom size: ";
    private static final String BYTES = "bytes";
    private static final String SPACE = " ";
    private static final String HEX = "%02X";
    private static final String STACK = "Stack";
    private static final int size = 0x01;
    private static final int INDEX = 0;
    private static final int MASK = 0xFF;
    private static final int MEMORY_STACK_MODULE = 16;
    private static final int DISAMBLER_COLUMN = 4;
    private static final int MODULE_HEX = 3;
    private static final int LEN = 1;
    private static final int ONE = 1;

    public static Boolean rom(String fileName, Memory memory) {
        int romSize = Utils.ZERO;
        Boolean result = false;
        try {
            final FileInputStream inputStream = new FileInputStream(fileName);
            byte data[] = new byte[size];
            for (int i = Memory.START_CHIP8_PROGRAMS; inputStream.read(data, Utils.ZERO, LEN) != -ONE; i++) {
                final short readByte = (short) (data[INDEX] >= Utils.ZERO ? data[INDEX] : (data[INDEX] & MASK)); // eq (data[0] + 0x100)
                memory.set(i, readByte);
                romSize = i - Memory.START_CHIP8_PROGRAMS;
            }
            result = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            return visualizationRoom(memory, romSize, result);
        }
    }

    private static Boolean visualizationRoom(Memory memory, double romSize, Boolean result) {
        System.out.println(ROM_SIZE + (romSize + 1) + SPACE + BYTES);
        System.out.println();
        System.out.println(STACK);

        for (int i = Memory.START_CHIP8_PROGRAMS; i <= (romSize + Memory.START_CHIP8_PROGRAMS); i++) {
            final short readByte = memory.get(i);
            final int memoryStack = Memory.START_CHIP8_PROGRAMS + i;
            if (i % MEMORY_STACK_MODULE == Utils.ZERO) {
                System.out.println();
                System.out.print(String.format(" " + HEX + "   ", memoryStack));
            }

            System.out.print(String.format(HEX + " ", readByte));

            if (i % DISAMBLER_COLUMN == MODULE_HEX)
                System.out.print("  ");
        }

        System.out.println();
        System.out.println();
        return result;
    }
}
