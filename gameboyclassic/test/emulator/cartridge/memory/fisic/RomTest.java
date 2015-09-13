package emulator.cartridge.memory.fisic;

import junit.framework.TestCase;

import static org.mockito.Mockito.spy;

public class RomTest extends TestCase {

    private Rom rom;
    private Rom romSpy;
    private byte[] data;

    public void setUp() throws Exception {
        super.setUp();
        data = new byte[100];
        rom = rom.Create(data);
        romSpy = spy(rom);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        rom = null;
        romSpy = null;
        data = null;
    }

    public void testGetData() throws Exception {
        final byte result = (byte) 0;
        final int addres = 50;
        final byte data = rom.getData(addres);
        assertTrue("Not same testGetData", data == result);
    }

    public void testSetData() throws Exception {
        final int addres = 20;
        final byte data = (byte) 0xA;
        rom.setData(addres, data);
        assertTrue("Not same testSetData", rom.getData(addres) == data);
    }

    public void testGetObject() throws Exception {
        assertSame("Not same testGetObject", rom.getObject(), data);
    }

    public void testSetObject() throws Exception {
        final byte[] _data = new byte[200];
        rom.setObject(_data);
        assertFalse("Not same testSetObject", rom.getObject() == data);
    }

    public void testSetObjectSame() throws Exception {
        final byte[] data = new byte[200];
        rom.setObject(data);
        assertTrue("Not same testSetObject", rom.getObject() == data);
    }

    public void testGetOffset() throws Exception {
        final int value = 0;
        assertSame("Not same testGetObject", rom.getOffset(), value);
    }

    public void testSetOffset() throws Exception {
        final int offset = 0xA000;
        rom.setOffset(offset);
        assertTrue("Not same testSetOffset",rom.getOffset() == offset);
    }

    public void testBank() throws Exception {
        final int addressResult = 0x4000;
        final int bank = 2;
        this.rom.bank(bank);
        assertTrue("Not same testBank", rom.getOffset() == addressResult);
    }
}