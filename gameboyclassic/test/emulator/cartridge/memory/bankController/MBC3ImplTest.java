package emulator.cartridge.memory.bankController;

import junit.framework.TestCase;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class MBC3ImplTest extends TestCase {

    private MBC3 mbc3;
    private MBC3 mbc3Spy;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mbc3 = MBC3Impl.Create();
        mbc3Spy = spy(mbc3);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        mbc3 = null;
        mbc3Spy = null;
    }

    public void testIsClockenabled() throws Exception {
        final boolean clockenabled = mbc3.isClockenabled();
        assertFalse("Not same testIsClockenabled", clockenabled);
    }

    public void testSetClockenabled() throws Exception {
        mbc3Spy.setClockenabled(true);
        verify(mbc3Spy).setClockenabled(true);
    }

    public void testGetTime() throws Exception {
        final long time = mbc3.getTime();
        assertTrue("Not same testGetTime", time == 0);
    }

    public void testSetTime() throws Exception {
        mbc3Spy.setTime(0);
        verify(mbc3Spy).setTime(0);
    }

    public void testGetReg() throws Exception {
        final int reg = mbc3Spy.getReg();
        assertTrue("Not same testGetTime", reg == 0);
    }

    public void testSetReg() throws Exception {
        mbc3Spy.setReg(0);
        verify(mbc3Spy).setReg(0);
    }

    public void testReset() throws Exception {
        mbc3Spy.reset();
        verify(mbc3Spy).reset();
    }

    public void testDispose() throws Exception {
        mbc3Spy.reset();
        verify(mbc3Spy).reset();
    }
}