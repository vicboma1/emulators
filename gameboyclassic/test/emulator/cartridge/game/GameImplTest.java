package emulator.cartridge.game;

import cartridge.memory.fisic.Rom;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameImplTest extends TestCase {

    private GameImpl gameMock;

    public void setUp() throws Exception {
        super.setUp();
        gameMock = mock(GameImpl.class);
    }

    public void tearDown() throws Exception {
        gameMock = null;
    }

    public void testRomBanks() throws Exception {
        final int romBanks = gameMock.romBanks();
        assertTrue("Not same testRomBanks", romBanks == 0);
    }

    public void testRamBanks() throws Exception {
        final int ramBanks = gameMock.ramBanks();
        assertTrue("Not same testRamBanks", ramBanks == 0);
    }

    public void testGetName() throws Exception {
        final String test = "Test";
        final byte[] data = new byte[0x4000];
        final Rom rom = new Rom(data);
        GameImpl game = new GameImpl(rom, test);
        assertTrue("Not same testGameName", test.equals(game.getName()));
    }

    public void testIndicator() throws Exception {
        final int indicator = gameMock.indicator();
        verify(gameMock).indicator();
    }

    public void testIsGbColor() throws Exception {
        final boolean gbColor = gameMock.isGbColor();
        verify(gameMock).isGbColor();
    }

    public void testCheck() throws Exception {
        final boolean isCheck = true;
        final Boolean aBoolean = gameMock.checkSum(isCheck);
        verify(gameMock).checkSum(isCheck);
    }

    public void testToString() throws Exception {
        final String result = "Rom name : test\\nRom original title : \u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\\nRom version : 0\\nLicense code : 0\\nOld license : Super GameBoy function won't work - Code : 0\\nGame boy game : Classic - Code : 0\\nDestination Region :  Japanese - Code : 0\\nGame boy indicator : Game Boy - Code : 0\\nChecksum = 0\\nActual checksum = 0\\nHeader checksum = 0\\nActual header checksum = 0\\nCartridge type = ROM\\nRom banks = 2 - Rom size = 32 Kb.\\nRam banks = 0 - Rom size = 0 bits - 0 Kb.\\nIs vertical blank interrupt handler ? true\\nIs lcd status interrupt handler ? true\\nIs timer overflow interrupt handler ? true\\nIs serial transfer completion interrupt handler ? true\\nIs high to lower of P10-P13 interrupt handler ? true\\n".replace("\\n", "");
        final int size = 0x4000;
        final byte[] data = new byte[size];
        final Rom rom = new Rom(data);
        final String test = "test";
        final GameImpl game = new GameImpl(rom, test);
        final String value = game.toString().replace("\n", "");
        assertTrue("Not same testToString", value.equals(result));
    }

    public void testNormalizeRom() throws Exception {
        final Rom rom = mock(Rom.class);
        gameMock.normalizeRom(rom);
        verify(gameMock).normalizeRom(rom);
    }

}