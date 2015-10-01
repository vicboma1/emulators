package emulator.cartridge.game;

import cartridge.memory.fisic.FisicMemory;
import cartridge.utils.MemoryControllersGameBoy;
import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.logger.api.Logger;
import utils.CheckSum;

/**
 * Created by vicboma on 14/08/15.
 */
public class GameImpl implements Game {

    public static final String SUPER_GAME_BOY_FUNCTION_WON_T_WORK = "Super GameBoy function won't work";
    public static final String NINTENDO = "Nintendo ";
    public static final char LN = '\n';
    public static final String ROM_NAME = "Rom name : ";
    public static final String ROM_ORIGINAL_TITLE = "Rom original title : ";
    public static final String ROM_VERSION = "Rom version : ";
    public static final String LICENSE_CODE = "License code : ";
    public static final String OLD_LICENSE = "Old license : ";
    public static final String GAME_BOY_GAME = "Game boy game : ";
    public static final String DESTINATION_REGION = "Destination Region :  ";
    public static final String GAME_BOY_INDICATOR = "Game boy indicator : ";
    public static final String CHECKSUM = "Checksum = ";
    public static final String ACTUAL_CHECKSUM = "Actual checksum = ";
    public static final String HEADER_CHECKSUM = "Header checksum = ";
    public static final String ACTUAL_HEADER_CHECKSUM = "Actual header checksum = ";
    public static final String CARTRIDGE_TYPE1 = "Cartridge type = ";
    public static final String ROM_BANKS = "Rom banks = ";
    public static final String RAM_BANKS1 = "Ram banks = ";
    public static final String IS_VERTICAL_BLANK_INTERRUPT_HANDLER = "Is vertical blank interrupt handler ? ";
    public static final String IS_LCD_STATUS_INTERRUPT_HANDLER = "Is lcd status interrupt handler ? ";
    public static final String IS_TIMER_OVERFLOW_INTERRUPT_HANDLER = "Is timer overflow interrupt handler ? ";
    public static final String IS_SERIAL_TRANSFER_COMPLETION_INTERRUPT_HANDLER = "Is serial transfer completion interrupt handler ? ";
    public static final String IS_HIGH_TO_LOWER_OF_P10_P13_INTERRUPT_HANDLER = "Is high to lower of P10-P13 interrupt handler ? ";
    public static final String CODE = " - Code : ";
    public static final String ROM_SIZE1 = " - Rom size = ";
    public static final String KB = " Kb.";
    public static final String B = " bits";

    // Nintendo Game Boy signature
    public static final int LOGO_SIZE = 0x30;// Size of Nintendo logo
    public static final short [] Logo = new short[]{ 0xCE, 0xed, 0x66, 0x66,  0xcc, 0x0d, 0x00, 0x0b, 0x03, 0x73, 0x00, 0x83, 0x00, 0x0c, 0x00, 0x0d, 0x00, 0x08, 0x11, 0x1f,  0x88, 0x89, 0x00, 0x0e, 0xdc, 0xcc, 0x6e, 0xe6, 0xdd, 0xdd, 0xd9, 0x99, 0xbb, 0xbb, 0x67, 0x63,  0x6e, 0x0e, 0xec, 0xcc, 0xdd, 0xdc, 0x99, 0x9f, 0xbb, 0xb9, 0x33, 0x3e };
    public static final int NINTENDO_LOGO = 0x0104;
    public static final int TITLE_GAME = 0x0134;
    public static final int GB_COLOR = 0x0143;
    public static final int LICENSEE_CODE_HI = 0x0144;
    public static final int LICENSEE_CODE_LO = 0x0145;
    public static final int GB_SGB_INDICATOR = 0x0146;
    public static final int CARTRIDGE_TYPE = 0x0147;
    public static final int ROM_SIZE = 0x0148;
    public static final int RAM_BANKS = 0x0149;
    public static final int DESTINATION_CODE = 0x014A;
    public static final int LICENSEE_CODE_OLD = 0x014B;
    public static final int MASK_ROM_VERSION = 0x014C;

    public static final int MAKS_INTERRUPTS = 0xD9;
    public static final int VERTICAL_BLANK_INTERRUPT_HANDLER = 0x0040;
    public static final int LCDCS_STATUS_INTERRUPT_HANDLER = 0x0048;
    public static final int TIMER_OVER_FLOW_INTERRUPT_HANDLER = 0x0050;
    public static final int SERIAL_TRANSFER_COMPLETAION_INTERRUPT_HANDLER = 0x0058;
    public static final int HIGH_TO_LOW_OF_P10_TO_P13_INTERRUPT_HANDLER = 0x0060;

    private int codeRegion;
    private String region;
    private String nameGame;
    private String romFileName;
    private int GbColorCode;
    private String GbColor;
    private String indicator;
    private String licenseeCodeOld;
    private int licenseCode;
    private int key_licenseeCodeOld;
    private int key_gbIndicator;
    private int romVersion;
    private String cartridge_type;
    private int romSize;
    private int romBanks;
    private int ramSize;
    private int ramBanks;
    private int checksum;
    private int actualChecksum;
    private int headerChecksum;
    private int actualHeaderChecksum;
    private boolean isVerticalBlankInterruptHandler;
    private boolean isLCDCStatusInterruptHandler;
    private boolean isTimerOverflowInterruptHandler;
    private boolean isSerialTransferCompletionInterruptHandler;
    private boolean isHighToLowOfP10ToP13InterruptHandler;
    private FisicMemory rom;

    @Inject
    public Logger logger;

    public static Game Create() {
        return new GameImpl();
    }

    public static Game Create(final FisicMemory rom, String romFileName) {
        return new GameImpl(rom, romFileName);
    }

    public GameImpl() {
    }

    public GameImpl(final FisicMemory _rom, String romFileName) {
        byte[] romBytes = _rom.getObject();
        romFileName(romFileName);
        originalRomName(romBytes);
        isGameClassicOrColor(romBytes);
        romVersion(romBytes);
        licenseCode(romBytes);
        indicator(romBytes);
        licenseCodeOld(romBytes);
        codeRegion(romBytes);
        cartridgeName(romBytes);
        romBankAndSize(romBytes);
        ramBankAndSize(romBytes);
        isVerticalBlankInterruptHandler(romBytes);
        isLCDCStatusInterruptHandler(romBytes);
        isTimerOverflowInterruptHandler(romBytes);
        isSerialTransferCompletionInterruptHandler(romBytes);
        isHighToLowOfP10ToP13InterruptHandler(romBytes);
    }


    @PostConstruct
    public void initialize(){
        logger.info(this);
    }

    private void isHighToLowOfP10ToP13InterruptHandler(byte[] rom) {
        isHighToLowOfP10ToP13InterruptHandler = rom[HIGH_TO_LOW_OF_P10_TO_P13_INTERRUPT_HANDLER] == MAKS_INTERRUPTS;
    }

    private void isSerialTransferCompletionInterruptHandler(byte[] rom) {
        isSerialTransferCompletionInterruptHandler = rom[SERIAL_TRANSFER_COMPLETAION_INTERRUPT_HANDLER] == MAKS_INTERRUPTS;
    }

    private void isTimerOverflowInterruptHandler(byte[] rom) {
        isTimerOverflowInterruptHandler = rom[TIMER_OVER_FLOW_INTERRUPT_HANDLER] == MAKS_INTERRUPTS;
    }

    private void isLCDCStatusInterruptHandler(byte[] rom) {
        isLCDCStatusInterruptHandler = rom[LCDCS_STATUS_INTERRUPT_HANDLER] == MAKS_INTERRUPTS;
    }

    private void isVerticalBlankInterruptHandler(byte[] rom) {
        isVerticalBlankInterruptHandler = rom[VERTICAL_BLANK_INTERRUPT_HANDLER] == MAKS_INTERRUPTS;
    }

    private void ramBankAndSize(byte[] rom) {
        final int key_ramBank = (int) rom[RAM_BANKS];
        ramBanks = MemoryControllersGameBoy.RAM_BANK.get(key_ramBank);
        ramSize = MemoryControllersGameBoy.RAM_SIZE.get(key_ramBank) * 1024;
    }

    private void romBankAndSize(byte[] rom) {
        final int key_romBank = (int) rom[ROM_SIZE];
        romBanks = MemoryControllersGameBoy.ROM_SIZE.get(key_romBank);    // Determine the number of 16kb rom banks
        romSize = (romBanks * 16);
    }

    private void cartridgeName(byte[] rom) {
        final int byteToHexCartridge =  rom[CARTRIDGE_TYPE];
        cartridge_type = MemoryControllersGameBoy.CARTRIDGE_TYPE.get(byteToHexCartridge);
        if (cartridge_type == null)
            cartridge_type = MemoryControllersGameBoy.UNKNOWN;
    }

    private void codeRegion(byte[] rom) {
        codeRegion = (int) rom[DESTINATION_CODE];
        region = MemoryControllersGameBoy.DESTINATION_CODE.get(codeRegion);
    }

    private void licenseCodeOld(byte[] rom) {
        key_licenseeCodeOld = (int) rom[LICENSEE_CODE_OLD];
        licenseeCodeOld = MemoryControllersGameBoy.LICENSEE_CODE_OLD.get(key_licenseeCodeOld);
        if (licenseeCodeOld == null)
            licenseeCodeOld = SUPER_GAME_BOY_FUNCTION_WON_T_WORK;
        else if (licenseeCodeOld.equals(MemoryControllersGameBoy.CHECK)) {
            final int codeHi = (int) rom[LICENSEE_CODE_HI];
            final int codeLo = (int) rom[LICENSEE_CODE_LO];
            licenseeCodeOld = NINTENDO + codeHi + " " + codeLo;
        }
    }

    private void indicator(byte[] rom) {
        key_gbIndicator = (int) rom[GB_SGB_INDICATOR];
        indicator = MemoryControllersGameBoy.GB_SGB_INDICATOR.get(key_gbIndicator);
    }

    private void licenseCode(byte[] rom) {
        licenseCode = (((int) rom[LICENSEE_CODE_HI]) << 4) | rom[LICENSEE_CODE_LO];
    }

    private void romVersion(byte[] rom) {
        romVersion = (int) rom[MASK_ROM_VERSION];
    }

    private void isGameClassicOrColor(byte[] rom) {
        GbColorCode = (int) rom[GB_COLOR];
        GbColor = MemoryControllersGameBoy.GB_COLOR.get(GbColorCode);
        if (GbColor == null)
            GbColor = MemoryControllersGameBoy.CLASSIC;
    }

    private void originalRomName(byte[] rom) {
        this.nameGame = new String(rom, TITLE_GAME, 16);
    }

    private void romFileName(String romFileName) {
        this.romFileName = romFileName;
    }

    @Override
    public void normalizeRom(FisicMemory rom) {
        this.rom = rom;
    }

    @Override
    public Boolean checkSum(final boolean isCheck) {

        if (!isCheck)
            return false;

        headerChecksum = CheckSum.headerChecksum;
        actualHeaderChecksum = CheckSum.actualHeaderChecksum;
        checksum = CheckSum.checkSum;
        actualChecksum = CheckSum.actualChecksum;

        return true;
    }

    @Override
    public int romBanks() {
        return romBanks;
    }

    @Override
    public int ramBanks() {
        return ramBanks;
    }

    @Override
    public String getName() {
        return romFileName;
    }

    @Override
    public int indicator() {
        return key_gbIndicator;
    }

    @Override
    public boolean isGbColor() {
        return GbColorCode == 0x80;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(LN)
                .append(ROM_NAME + romFileName + LN)
                .append(ROM_ORIGINAL_TITLE + nameGame + LN)
                .append(ROM_VERSION + romVersion + LN)
                .append(LICENSE_CODE + licenseCode + LN)
                .append(OLD_LICENSE + licenseeCodeOld + CODE + key_licenseeCodeOld + LN)
                .append(GAME_BOY_GAME + GbColor + CODE + GbColorCode + LN)
                .append(DESTINATION_REGION + region + CODE + codeRegion + LN)
                .append(GAME_BOY_INDICATOR + indicator + CODE + this.key_gbIndicator + LN)
                .append(CHECKSUM + checksum + LN)
                .append(ACTUAL_CHECKSUM + actualChecksum + LN)
                .append(HEADER_CHECKSUM + headerChecksum + LN)
                .append(ACTUAL_HEADER_CHECKSUM + actualHeaderChecksum + LN)
                .append(CARTRIDGE_TYPE1 + cartridge_type + LN)
                .append(ROM_BANKS + romBanks + ROM_SIZE1 + romSize + KB + LN)
                .append(RAM_BANKS1 + ramBanks + ROM_SIZE1 + ramSize + B +" - "+(ramSize/1024) + KB+ LN)
                .append(IS_VERTICAL_BLANK_INTERRUPT_HANDLER + !isVerticalBlankInterruptHandler + LN)
                .append(IS_LCD_STATUS_INTERRUPT_HANDLER + !isLCDCStatusInterruptHandler + LN)
                .append(IS_TIMER_OVERFLOW_INTERRUPT_HANDLER + !isTimerOverflowInterruptHandler + LN)
                .append(IS_SERIAL_TRANSFER_COMPLETION_INTERRUPT_HANDLER + !isSerialTransferCompletionInterruptHandler + LN)
                .append(IS_HIGH_TO_LOWER_OF_P10_P13_INTERRUPT_HANDLER + !isHighToLowOfP10ToP13InterruptHandler + LN);

        return sb.toString();

    }


}