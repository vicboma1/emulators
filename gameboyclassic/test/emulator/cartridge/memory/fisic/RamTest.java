package emulator.cartridge.memory.fisic;

import junit.framework.TestCase;

import static org.mockito.Mockito.spy;

public class RamTest extends TestCase {

    private Ram ram;
    private Ram ramSpy;
    private byte[] data;

    public void setUp() throws Exception {
        super.setUp();
        data = new byte[100];
        ram = Ram.Create(data);
        ramSpy = spy(ram);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        ram = null;
        ramSpy = null;
        data = null;
    }

    public void testGetData() throws Exception {
        final byte result = (byte) 0;
        final int addres = 50;
        final byte data = ram.getData(addres);
        assertTrue("Not same testGetData", data == result);
    }

    public void testSetData() throws Exception {
        final int addres = 20;
        final byte data = (byte) 0xF;
        ram.setData(addres, data);
        assertTrue("Not same testSetData", ram.getData(addres) == data);
    }

    public void testGetObject() throws Exception {
        assertSame("Not same testGetObject", ram.getObject(), data);
    }

    public void testSetObject() throws Exception {
        final byte[] _data = new byte[200];
        ram.setObject(_data);
        assertFalse("Not same testSetObject", ram.getObject() == data);
    }

    public void testSetObjectSame() throws Exception {
        final byte[] data = new byte[200];
        ram.setObject(data);
        assertTrue("Not same testSetObject", ram.getObject() == data);
    }

    public void testGetOffset() throws Exception {
        final int value = 0;
        assertSame("Not same testGetObject", ram.getOffset(), value);
    }

    public void testSetOffset() throws Exception {
        final int offset = 0xA000;
        ram.setOffset(offset);
        assertTrue("Not same testSetOffset",ram.getOffset() == offset);
    }

    public void testBank() throws Exception {
        final int addressResult = -0xA000;
        final int bank = 0;
        this.ram.bank(bank);
        assertTrue("Not same testBank", ram.getOffset() == addressResult);
    }
}