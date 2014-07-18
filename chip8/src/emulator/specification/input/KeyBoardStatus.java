package emulator.specification.input;

import emulator.utils.Utils;

/**
 * Created by vicboma on 08/07/14.
 */
public class KeyBoardStatus {

    public static final short[] map = {0x01, 0x02, 0x03, 0x0C, 0x04, 0x05, 0x06, 0x0D, 0x07, 0x08, 0x09, 0x0E, 0x0A, 0x00, 0x0B, 0x0F};
    public static final short[] unMap = {0x0D, 0x00, 0x01, 0x02, 0x04, 0x05, 0x06, 0x08, 0x09, 0x0A, 0x0C, 0x0E, 0x03, 0x07, 0x0B, 0x0F};

    private static final int RESET = 255; //0xff

    public short previousKeyPressed;
    public short actualKeyPressed;
    public boolean isKeyPressed;
    public boolean isOtherKeyPressed;

    public KeyBoardStatus() {
        previousKeyPressed = Utils.ZERO;
        actualKeyPressed = Utils.ZERO;
        isKeyPressed = Utils.FALSE;
        isOtherKeyPressed = Utils.FALSE;
    }

    public void refresh() {
        actualKeyPressed = RESET;

        if (isKeyPressed && previousKeyPressed != actualKeyPressed) {
            actualKeyPressed = previousKeyPressed;
            isOtherKeyPressed = true;
        }
    }

    public void reset() {
        isKeyPressed = Utils.FALSE;
        isOtherKeyPressed = Utils.FALSE;
    }

    public void process(KeyBoard keyBoard) {
        for (int i = 0; i < map.length; i++) {
            if (keyBoard.get(i)) {
                actualKeyPressed = map[i];
                isKeyPressed = true;
            }
        }
    }
}
