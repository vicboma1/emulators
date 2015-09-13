package emulator.cartridge.memory.bankController;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class MBC1ImplTest extends TestCase {

    private MBC1 mbc1;

    public void setUp() throws Exception {
        super.setUp();
        mbc1 = MBC1Impl.Create();
    }

    public void tearDown() throws Exception {
        mbc1 = null;
    }

    public void testIsModeEnabled() throws Exception {
        final boolean modeEnabled = mbc1.isModeEnabled();
        assertTrue("Not same testIsModeEnabled", modeEnabled);
    }

    public void testSetModeEnabled() throws Exception {
        final MBC1 mbc1Mock = mock(MBC1.class);
        mbc1Mock.setModeEnabled(false);
        verify(mbc1Mock).setModeEnabled(false);
    }

    public void testIsRamEnabled() throws Exception {
        final boolean ramEnabled = mbc1.isRamEnabled();
        assertFalse("Not same testIsModeEnabled", ramEnabled);

    }

    public void testSetRamEnabled() throws Exception {
        final MBC1 mbc1Spy = spy(mbc1);
        mbc1Spy.setRamEnabled(true);
        verify(mbc1Spy).setRamEnabled(true);
    }

    public void testDispose() throws Exception {
        final MBC1 mbc1Spy = spy(mbc1);
        mbc1Spy.dispose();
        verify(mbc1Spy).dispose();
    }

    public void testReset() throws Exception {
        final MBC1 mbc1Spy = spy(mbc1);
        mbc1Spy.reset();
        verify(mbc1Spy).reset();
    }

}