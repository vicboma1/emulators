package emulator.utils;

/**
 * Created by vicboma on 08/08/14.
 */
public class CheckSum {

    public final static int HI = 0x14E;
    public final static int LO = 0x14F;
    public static final int DESP = 8;
    public static final int MASK = 0x0000FFFF;

    public static boolean validate(byte [] rom ){

        int checkSum = (Operation.unsign(rom[HI]) << DESP) + Operation.unsign(rom[LO]);

        int sumTotal = 0;
        for (int r =0; r < rom.length; r++) {
            if ((r != HI) && (r != LO)) {
                sumTotal = (sumTotal + Operation.unsign(rom[r])) & MASK;
            }
        }

        return (checkSum == sumTotal);

        }
    }

