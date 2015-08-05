package emulator.cartridge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicboma on 03/12/14.
 */
public class MemoryControllersGameBoy {

    public static final String MBC1 = "MBC1";
    public static final String RAM = "RAM";
    public static final String SRAM = "SRAM";
    public static final String BATT = "BATT";
    public static final String UNKNOWN = "Unknown";
    public static final String MMM01 = "MMM01";
    public static final String ROM = "ROM";
    public static final String MBC2 = "MBC2";
    public static final String MBC3 = "MBC3";
    public static final String RTC = "RTC";
    public static final String NO_RTC = "NO_RTC";
    public static final String MBC5 = "MBC5";
    public static final String NO_RUMBLE = "NO_RUMBLE";
    public static final String RUMBLE = "RUMBLE";
    public static final String BANDAI_TAMA5 = "Bandai TAMA5";
    public static final String HUDSON_HUC_3 = "Hudson HuC-3";
    public static final String HUDSON_HUC_1 = "Hudson HuC-1";
    public static final String POCKET_CAMERA = "POCKET CAMERA";


    /*
     *  Chapter 8: Game Boy Memory Controllers (MBC) Page 212 - 230
     *
     */
    public static final List<String> table = new ArrayList<String>() {
        {
            add(ROM);                                                               /* 00H ROM*/
            add(ROM + "+" + MBC1);                                                  /* 01H MBC1*/
            add(ROM + "+" + MBC1 + "+" + RAM);                                      /* 02H MBC1*/
            add(ROM + "+" + MBC1 + "+" + RAM + "+" + BATT);                         /* 03H MBC1*/
            add(UNKNOWN);                                                           /* 04H */
            add(ROM + "+" + MBC2);                                                  /* 05H MBC2*/
            add(ROM + "+" + MBC2 + "+" + BATT);                                     /* 06H MBC2*/
            add(UNKNOWN);                                                           /* 07H */
            add(ROM + "+" + RAM);                                                   /* 08H ROM*/
            add(ROM + "+" + RAM + "+" + BATT);                                      /* 09H ROM*/
            add(UNKNOWN);                                                           /* 0AH */
            add(ROM + "+" + MMM01);                                                 /* 0BH */
            add(ROM + "+" + MMM01 + "+" + SRAM);                                    /* 0CH */
            add(ROM + "+" + MMM01 + "+" + SRAM + "+" + BATT);                       /* 0DH */
            add(UNKNOWN);                                                           /* 0EH */
            add(ROM + "+" + MBC3 + "+" + RTC + "+" + BATT);                         /* 0FH MBC3 */
            add(ROM + "+" + MBC3 + "+" + RTC + "+" + RAM + "+" + BATT);             /* 10H MBC3*/
            add(ROM + "+" + MBC3 + "+" + NO_RTC + "+" + RAM);                       /* 11H MBC3*/
            add(ROM + "+" + MBC3 + "+" + NO_RTC + "+" + RAM + "+" + BATT);          /* 12H MBC3*/
            add(UNKNOWN);                                                           /* 13H */
            add(UNKNOWN);                                                           /* 14H */
            add(UNKNOWN);                                                           /* 15H */
            add(UNKNOWN);                                                           /* 16H */
            add(UNKNOWN);                                                           /* 17H */
            add(UNKNOWN);                                                           /* 18H */
            add(ROM + "+" + MBC5 + "+" + NO_RUMBLE);                                /* 19H MBC5*/
            add(ROM + "+" + MBC5 + "+" + NO_RUMBLE + "+" + SRAM);                   /* 1AH MBC5*/
            add(ROM + "+" + MBC5 + "+" + NO_RUMBLE + "+" + SRAM + "+" + BATT);      /* 1BH MBC5*/
            add(ROM + "+" + MBC5 + "+" + RUMBLE);                                   /* 1CH MBC5*/
            add(ROM + "+" + MBC5 + "+" + RUMBLE + "+" + SRAM);                      /* 1DH MBC5*/
            add(ROM + "+" + MBC5 + "+" + RUMBLE + "+" + SRAM + "+" + BATT);         /* 1EH MBC5*/
            add(POCKET_CAMERA);                                                     /* 1FH POCKET_CAMERA*/
            //    add(BANDAI_TAMA5);                                                /* FD BANDAI_TAMA5*/
            //    add(HUDSON_HUC_3);                                                /* FE HUDSON_HUC_3*/
            //    add(HUDSON_HUC_1);                                                /*  FF HUDSON_HUC_1*/
        }
    };
}















