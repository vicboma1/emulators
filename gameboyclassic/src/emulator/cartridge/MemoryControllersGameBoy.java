package emulator.cartridge;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vicboma on 03/12/14.
 *
 * Los valores de las claves están referenciadas en Hexadecimal mediante String.
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


    public static final String JAPANESE = "Japanese";
    public static final String NON_JAPANESE = "Non-Japanese";


    public static final String CHECK = "Check";


    public static final String CLASSIC = "Classic";
    public static final String COLOR = "Color";
    /**
     * $80 = Color GB, $00 or other = not Color GB
     */
    public static final Map<String,String> GB_COLOR = new HashMap(){
        {
            put("00", CLASSIC);
            put("80", COLOR);
        }
    };


    /**
     * GB/SGB Indicator
     */
    public static final Map<String, String> GB_SGB_INDICATOR = new HashMap(){
        {
            put("00", "Game Boy");
            put("03", "Super GameBoy functions");
        }
    };

    /**
     * LICENSEE CODE OLD
     *
     * 33 - Check 0144/0145 for Licensee code.
     * 79 - Accolade
     * A4 - Konami
     * (Super GameBoy function won't work
     * if <> $33.)
     *
     */
    public static final Map<String, String> LICENSEE_CODE_OLD = new HashMap(){
        {
            put("33", CHECK);
            put("79", "Accolade");
            put("A4", "Konami");
        }
    };


    /**
     * ROM SIZE
     *
     * Index - Size Banks
     *
     * Example : 0 - 256Kbit  = 32KByte  = 2 Banks
     *         : 1 - 512kBits = 64Kbytes = 4 Banks
     *         ...
     *
     */
    public static final Map<String, Integer> ROM_SIZE = new HashMap(){
        {
            put("00", 2);     put("01", 4);   put("02", 8);   put("03", 16);    put("04", 32);
            put("05", 64);    put("06", 128); put("07", 256); put("52", 72); put("53", 80);
            put("54", 96);
            put("4E", 64); //ROM + "+" + MBC3 + "+" + NO_RTC + "+" + RAM +"+"+ BATT);
        }
    };


    /**
     * RAM SIZE
     *
     * Index - Size Banks
     *
     * Example : 0 - None
     *         : 1 - 16kBit = 2kB = 1bank
     *         : 2 - 64kBit = 8kB = 1bank
     *         ...
     *
     */
    public static final Map<String, Integer> RAM_SIZE = new HashMap(){
        {
            put("00", 0);     put("01", 1);   put("02", 1);   put("03", 4);  put("04", 16);  put("05", 64);
        }
    };

    /**
     * Destination code:
     * 0 - Japanese
     * 1 - Non-Japanese
     */
    public static final Map<String, String> DESTINATION_CODE = new HashMap(){
        {
            put("00", JAPANESE);     put("01", NON_JAPANESE);
        }
    };

     /*
     *  Chapter 8: Game Boy Memory Controllers (MBC) Page 212 - 230
     *
     */
    public static final Map<String,String> CARTRIDGE_TYPE = new HashMap<String,String>() {
        {
            put("00", ROM);
            put("01", ROM + "+" + MBC1);
            put("02", ROM + "+" + MBC1 + "+" + RAM);
            put("03", ROM + "+" + MBC1 + "+" + RAM + "+" + BATT);
            put("05", ROM + "+" + MBC2);
            put("06", ROM + "+" + MBC2 + "+" + BATT);
            put("08", ROM + "+" + RAM);
            put("09", ROM + "+" + RAM + "+" + BATT);
            put("0B", ROM + "+" + MMM01);
            put("0C", ROM + "+" + MMM01 + "+" + SRAM);
            put("0D", ROM + "+" + MMM01 + "+" + SRAM + "+" + BATT);
            put("0F", ROM + "+" + MBC3 + "+" + RTC + "+" + BATT);
            put("10",ROM + "+" + MBC3 + "+" + RTC + "+" + RAM + "+" + BATT);
            put("11",ROM + "+" + MBC3 + "+" + NO_RTC);
            put("12",ROM + "+" + MBC3 + "+" + NO_RTC + "+" + RAM);
            put("13",ROM + "+" + MBC3 + "+" + NO_RTC + "+" + RAM +"+"+ BATT);
            put("19", ROM + "+" + MBC5 + "+" + NO_RUMBLE);
            put("1A", ROM + "+" + MBC5 + "+" + NO_RUMBLE + "+" + SRAM);
            put("1B", ROM + "+" + MBC5 + "+" + NO_RUMBLE + "+" + SRAM + "+" + BATT);
            put("1C", ROM + "+" + MBC5 + "+" + RUMBLE);
            put("1D", ROM + "+" + MBC5 + "+" + RUMBLE + "+" + SRAM);
            put("1E", ROM + "+" + MBC5 + "+" + RUMBLE + "+" + SRAM + "+" + BATT);
            put("1F", POCKET_CAMERA);
            put("FD",BANDAI_TAMA5);
            put("FE",HUDSON_HUC_3);
            put("FF",HUDSON_HUC_1);
        }
    };
}















