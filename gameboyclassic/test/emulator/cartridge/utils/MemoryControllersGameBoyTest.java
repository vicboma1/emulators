package emulator.cartridge.utils;

import junit.framework.TestCase;

public class MemoryControllersGameBoyTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {

    }

    public void testGB_COLOR() throws Exception {
        final String result = "{0=Classic, 128=Color}";
        final String toStringGB_COLOR = MemoryControllersGameBoy.GB_COLOR.toString();
        assertTrue("Not same testGB_COLOR",toStringGB_COLOR.equals(result));
    }

    public void testGB_SGB_INDICATOR() throws Exception {
        final String result = "{0=Game Boy, 3=Super GameBoy functions}";
        final String toStringGB_SGB_INDICATOR = MemoryControllersGameBoy.GB_SGB_INDICATOR.toString();
        assertTrue("Not same GB_SGB_INDICATOR",toStringGB_SGB_INDICATOR.equals(result));
    }

    public void testGLICENSEE_CODE_OLD() throws Exception {
        final String result = "{164=Konami, 51=Check, 121=Accolade}";
        final String toStringLICENSEE_CODE_OLD = MemoryControllersGameBoy.LICENSEE_CODE_OLD.toString();
        assertTrue("Not same LICENSEE_CODE_OLD",toStringLICENSEE_CODE_OLD.equals(result));
    }

    public void testROM_SIZE() throws Exception {
        final String result = "{84=96, 83=80, 82=72, 78=64, 7=256, 6=128, 5=64, 4=32, 3=16, 2=8, 1=4, 0=2}";
        final String toStringROM_SIZE = MemoryControllersGameBoy.ROM_SIZE.toString();
        assertTrue("Not same ROM_SIZE",toStringROM_SIZE.equals(result));
    }

    public void testRAM_BANK() throws Exception {
        final String result = "{0=0, 1=1, 2=1, 3=4, 4=16, 5=64}";
        final String toStringRAM_BANK = MemoryControllersGameBoy.RAM_BANK.toString();
        assertTrue("Not same RAM_BAKN",toStringRAM_BANK.equals(result));
    }

    public void testRAM_SIZE() throws Exception {
        final String result = "{0=0, 1=2, 2=8, 3=32, 4=128, 5=256}";
        final String toStringRAM_SIZE = MemoryControllersGameBoy.RAM_SIZE.toString();
        assertTrue("Not same RAM_SIZE",toStringRAM_SIZE.equals(result));
    }

    public void testDESTINATION_CODE() throws Exception {
        final String result = "{1=Non-Japanese, 0=Japanese}";
        final String toStringDESTINATION_CODE = MemoryControllersGameBoy.DESTINATION_CODE.toString();
        assertTrue("Not same DESTINATION_CODE",toStringDESTINATION_CODE.equals(result));
    }

    public void testCARTRIDGE_TYPE() throws Exception {
        final String result = "{0=ROM, -1=Hudson HuC-1, 1=ROM+MBC1, -2=Hudson HuC-3, 2=ROM+MBC1+RAM, -3=Bandai TAMA5, 3=ROM+MBC1+RAM+BATT, -4=POCKET CAMERA, 5=ROM+MBC2, 6=ROM+MBC2+BATT, 8=ROM+RAM, 9=ROM+RAM+BATT, 11=ROM+MMM01, 12=ROM+MMM01+SRAM, 13=ROM+MMM01+SRAM+BATT, 15=ROM+MBC3+RTC+BATT, 16=ROM+MBC3+RTC+RAM+BATT, 17=ROM+MBC3+NO_RTC, 18=ROM+MBC3+NO_RTC+RAM, 19=ROM+MBC3+NO_RTC+RAM+BATT, 25=ROM+MBC5+NO_RUMBLE, 26=ROM+MBC5+NO_RUMBLE+SRAM, 27=ROM+MBC5+NO_RUMBLE+SRAM+BATT, 28=ROM+MBC5+RUMBLE, 29=ROM+MBC5+RUMBLE+SRAM, 30=ROM+MBC5+RUMBLE+SRAM+BATT}";
        final String toStringCARTRIDGE_TYPE = MemoryControllersGameBoy.CARTRIDGE_TYPE.toString();
        assertTrue("Not same CARTRIDGE_TYPE",toStringCARTRIDGE_TYPE.equals(result));
    }
}