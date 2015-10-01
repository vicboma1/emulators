package emulator.utils;

/**
 * Created by vicboma on 10/09/14.
 */
public class Operation {

    /**
     * 16-bits to signed 8-bit
     */
    public static Short unsign(Short b) {
        return _unsign(b);
    }

    /**
     * signed 8-bit
     */
    public static Short unsign(Byte b) {
        return _unsign(b);
    }

    private static Short _unsign(short b) {
        final short result = (b >= 0) ?  b : (short) (0x100 + b);
        return result;

    }

    public static final String hexChars = "0123456789ABCDEF";

    /**
     * string of an 8-bits in hx for Debug
     */
    public static String hexByte(int value) {
        final char shiftL = hexChars.charAt(value >> 4);
        final char mask = hexChars.charAt(value & 0x0F);
        String s = new Character(shiftL).toString();
        s += new Character(mask).toString();

        return s;
    }

    /**
     * string of an 16-bit in hx for Debug
     */
    public static String hexShort(Integer value) {
        String high = hexByte((value & 0x0000FF00) >> 8);
        String low = hexByte(value & 0x000000FF);
        return new String(high + low);
    }

    public static int mergeTo16bits(short big, short little) {
        return ((big << 8) | little);
    }
}
