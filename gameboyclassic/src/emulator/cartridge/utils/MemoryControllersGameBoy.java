package emulator.cartridge.utils;

import java.util.HashMap;
import java.util.Hashtable;
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
    public static final String ACCOLADE = "Accolade";
    public static final String KONAMI = "Konami";

    public static final String CLASSIC = "Classic";
    public static final String COLOR = "Color";

    public static final String GAME_BOY = "Game Boy";
    public static final String SUPER_GAME_BOY_FUNCTIONS = "Super GameBoy functions";


    /**
     * $80 = Color GB, $00 or other = not Color GB
     */
    public static final Map<Integer,String> GB_COLOR = new HashMap(){
        {
            put(0x00, CLASSIC);
            put(0x80, COLOR);
        }
    };


    /**
     * GB/SGB Indicator
     */
    public static final Map<Integer, String> GB_SGB_INDICATOR = new HashMap(){
        {
            put(0x00, GAME_BOY);
            put(0x03, SUPER_GAME_BOY_FUNCTIONS);
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
    public static final Map<Integer, String> LICENSEE_CODE_OLD = new Hashtable(){
        {
            put(0x33, CHECK);
            put(0x79, ACCOLADE);
            put(0xA4, KONAMI);
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
    public static final Map<Integer, Integer> ROM_SIZE = new Hashtable(){
        {
            put(0x00, 2);     put(0x01, 4);   put(0x02, 8);   put(0x03, 16); put(0x04, 32);
            put(0x05, 64);    put(0x06, 128); put(0x07, 256); put(0x52, 72); put(0x53, 80);
            put(0x54, 96);
            put(0x4E, 64); //ROM + "+" + MBC3 + "+" + NO_RTC + "+" + RAM +"+"+ BATT);
        }
    };


    /**
     * RAM SIZE
     *
     * Index - Banks
     *
     * Example : 0 - None
     *         : 1 - 16kBit = 2kB = 1bank
     *         : 2 - 64kBit = 8kB = 1bank
     *         ...
     *
     */
    public static final Map<Integer, Integer> RAM_BANK = new HashMap(){
        {
            put(0x00, 0);     put(0x01, 1);   put(0x02, 1);   put(0x03, 4);  put(0x04, 16);  put(0x05, 64);
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
    public static final Map<Integer, Integer> RAM_SIZE = new HashMap(){
        {
            put(0x00, 0);     put(0x01, 2);   put(0x02, 8);   put(0x03, 32);  put(0x04, 128);  put(0x05, 256);
        }
    };

    /**
     * Destination code:
     * 0 - Japanese
     * 1 - Non-Japanese
     */
    public static final Map<Integer, String> DESTINATION_CODE = new Hashtable(){
        {
            put(0x00, JAPANESE);     put(0x01, NON_JAPANESE);
        }
    };

     /*
     *  Chapter 8: Game Boy Memory Controllers (MBC) Page 212 - 230
     *
     */
    public static final Map<Integer,String> CARTRIDGE_TYPE = new HashMap() {
        {
            put(0x00, ROM);
            put(0x01, ROM + "+" + MBC1);
            put(0x02, ROM + "+" + MBC1 + "+" + RAM);
            put(0x03, ROM + "+" + MBC1 + "+" + RAM + "+" + BATT);
            put(0x05, ROM + "+" + MBC2);
            put(0x06, ROM + "+" + MBC2 + "+" + BATT);
            put(0x08, ROM + "+" + RAM);
            put(0x09, ROM + "+" + RAM + "+" + BATT);
            put(0x0B, ROM + "+" + MMM01);
            put(0x0C, ROM + "+" + MMM01 + "+" + SRAM);
            put(0x0D, ROM + "+" + MMM01 + "+" + SRAM + "+" + BATT);
            put(0x0F, ROM + "+" + MBC3 + "+" + RTC + "+" + BATT);
            put(0x10, ROM + "+" + MBC3 + "+" + RTC + "+" + RAM + "+" + BATT);
            put(0x11, ROM + "+" + MBC3 + "+" + NO_RTC);
            put(0x12, ROM + "+" + MBC3 + "+" + NO_RTC + "+" + RAM);
            put(0x13, ROM + "+" + MBC3 + "+" + NO_RTC + "+" + RAM +"+"+ BATT);
            put(0x19, ROM + "+" + MBC5 + "+" + NO_RUMBLE);
            put(0x1A, ROM + "+" + MBC5 + "+" + NO_RUMBLE + "+" + SRAM);
            put(0x1B, ROM + "+" + MBC5 + "+" + NO_RUMBLE + "+" + SRAM + "+" + BATT);
            put(0x1C, ROM + "+" + MBC5 + "+" + RUMBLE);
            put(0x1D, ROM + "+" + MBC5 + "+" + RUMBLE + "+" + SRAM);
            put(0x1E, ROM + "+" + MBC5 + "+" + RUMBLE + "+" + SRAM + "+" + BATT);
            put(0x1F, POCKET_CAMERA);
            put(0xFD, BANDAI_TAMA5);
            put(0xFE, HUDSON_HUC_3);
            put(0xFF, HUDSON_HUC_1);
        }
    };
}















