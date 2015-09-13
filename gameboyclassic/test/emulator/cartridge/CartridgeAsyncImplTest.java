package emulator.cartridge;

import junit.framework.TestCase;
import utils.window.Window;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

public class CartridgeAsyncImplTest extends TestCase {

    private CartridgeAsyncImpl cartridgeAsync;

    public void setUp() throws Exception {
        super.setUp();
        cartridgeAsync = mock(CartridgeAsyncImpl.class);
    }

    public void tearDown() throws Exception {
        cartridgeAsync = null;
    }

    public void testGBIndicator() throws Exception {
        final int value = 1;
        when(cartridgeAsync.gBIndicator()).thenReturn(value);
        final int gBIndicator = cartridgeAsync.gBIndicator();
        assertTrue("Not same testGBIndicator", gBIndicator == value);
    }

    public void testIsGbColor() throws Exception {
        final boolean result = true;
        when(cartridgeAsync.isGbColor()).thenReturn(result);
        final boolean isGbColor = cartridgeAsync.isGbColor();
        assertTrue("Not same testIsGbColor", isGbColor == result);
    }

    public void testLoad() throws Exception {
        final CompletableFuture result = CompletableFuture.completedFuture(Void.TYPE);
        final String testFile = "testFile";
        final Window mock = mock(Window.class);
        when(cartridgeAsync.load(testFile, mock)).thenReturn(result);
        final CompletableFuture load = cartridgeAsync.load(testFile, mock);
        assertTrue("Not same testLoad", load == result);
    }

    public void testRead() throws Exception {
        final int address = 0xFF;
        final short value = 0x50;
        final CompletableFuture<Short> result = CompletableFuture.completedFuture(value);
        when(cartridgeAsync.read(address)).thenReturn(result);
        final CompletableFuture load = cartridgeAsync.read(address);
        assertTrue("Not same testRead", load == result);
    }

    public void testWrite() throws Exception {
        final int address = 0xFF;
        final int data = 0x4444;
        cartridgeAsync.write(address, data);
        verify(cartridgeAsync).write(address, data);
    }

    public void testDispose() throws Exception {
        cartridgeAsync.dispose();
        verify(cartridgeAsync).dispose();
    }

    public void testReset() throws Exception {
        cartridgeAsync.reset();
        verify(cartridgeAsync).reset();
    }
}