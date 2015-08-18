package emulator.cartridge.game;

import cartridge.memory.fisic.FisicMemory;
import cartridge.utils.MemoryControllersGameBoy;
import utils.CheckSum;

/**
 * Created by vicboma on 14/08/15.
 */
public class GameImpl implements Game {

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
    public static final String SUPER_GAME_BOY_FUNCTION_WON_T_WORK = "Super GameBoy function won't work";
    public static final String NINTENDO = "Nintendo ";

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
    private int byteToHexCartridge; // to debug errors
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
    public static Game Create(){ return  new GameImpl(); }

    public static Game Create(final FisicMemory rom, String romFileName){ return  new GameImpl(rom,romFileName);}

    public GameImpl(){}

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
        romSize = romBanks * 16;
    }

    private void cartridgeName(byte[] rom) {
        byteToHexCartridge = (int) rom[CARTRIDGE_TYPE];
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
            licenseeCodeOld = NINTENDO +codeHi + " " + codeLo;
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
    public final void normalizeRom(FisicMemory rom){
        this.rom = rom;
    }

    @Override
    public final Boolean checkSum(){

        if(!CheckSum.validate(this.rom.getObject()))
            return false;

            headerChecksum = CheckSum.headerChecksum;
            actualHeaderChecksum = CheckSum.actualHeaderChecksum;
            checksum = CheckSum.checkSum;
            actualChecksum = CheckSum.actualChecksum;

            return true;
    }

    @Override
    public final int romBanks() {
        return romBanks;
    }

    @Override
    public final int byteToHexCartridge() {
        return byteToHexCartridge;
    }

    @Override
    public final int ramBanks() {
        return ramBanks;
    }

    @Override
    public String getName() {
        return romFileName;
    }

    @Override
    public final int indicator() { return key_gbIndicator; }

    @Override
    public final boolean isGbColor() { return GbColorCode == 0x80; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder()
                .append("\nRom name : " + romFileName + "\n")
                .append("Rom original title : " + nameGame + "\n")
                .append("Rom version : " + romVersion + "\n")
                .append("License code : " + licenseCode + "\n")
                .append("Old license : " + licenseeCodeOld +" - Code : "+key_licenseeCodeOld+"\n")
                .append("Game boy game : " + GbColor + " - Code : "+GbColorCode+"\n")
                .append("Destination Region :  "+region +" - Code : "+ codeRegion + "\n")
                .append("Game boy indicator : " +indicator +" - Code : "+this.key_gbIndicator+"\n")
                .append("Checksum = " + checksum + "\n")
                .append("Actual checksum = " + actualChecksum + "\n")
                .append("Header checksum = " + headerChecksum + "\n")
                .append("Actual header checksum = " + actualHeaderChecksum + "\n")
                .append("Cartridge type = " + cartridge_type + "\n")
                .append("Rom banks = " + romBanks + " - Rom size = " + romSize +" Kb.\n")
                .append("Ram banks = " + ramBanks + " - Ram size = " + ramSize+ "\n")
                .append("Is vertical blank interrupt handler ? " + !isVerticalBlankInterruptHandler + "\n")
                .append("Is lcd status interrupt handler ? " + !isLCDCStatusInterruptHandler + "\n")
                .append("Is timer overflow interrupt handler ? " + !isTimerOverflowInterruptHandler + "\n")
                .append("Is serial transfer completion interrupt handler ? " + !isSerialTransferCompletionInterruptHandler + "\n")
                .append("Is high to lower of P10-P13 interrupt handler ? " + !isHighToLowOfP10ToP13InterruptHandler + "\n");

        return sb.toString();

    }


}