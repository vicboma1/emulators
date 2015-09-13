package emulator.cartridge.battery;

import cartridge.game.GameImpl;
import cartridge.memory.fisic.Ram;
import cartridge.memory.fisic.Rom;
import junit.framework.TestCase;
import utils.shell.Logger;
import utils.shell.console.Console;
import utils.shell.pool.LoggerPoolAsyncImpl;

import java.awt.*;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

public class BatteryImplTest extends TestCase {

    public BatteryImpl battery;

    public void setUp() throws Exception {
        super.setUp();
        battery = new BatteryImpl();
    }

    public void tearDown() throws Exception {
        battery = null;
    }

    public void testSaveRamNull() throws Exception {
        final GameImpl gameMock = mock(GameImpl.class);
        final Ram ramMock = mock(Ram.class);
        final CompletableFuture<ByteBuffer> byteBufferCompletableFuture = battery.saveRam(gameMock, ramMock);
        assertNull("Not same testSaveRam", byteBufferCompletableFuture.get());
    }

    public void testSaveRam() throws Exception {
        final Dialog mock = mock(Dialog.class);
        final LoggerPoolAsyncImpl mock1 = mock(LoggerPoolAsyncImpl.class);
        final Console create = Logger.Create(mock, mock1);

        final String result = "java.nio.HeapByteBuffer[pos=0 lim=16384 cap=16384]";
        final byte[] data = new byte[0x4000];
        final Rom rom = new Rom(data);
        final String romFileName = "test.gb";
        GameImpl game = new GameImpl(rom, romFileName);
        GameImpl gameSpy = spy(game);
        final int resultRamBanks = 1;
        when(gameSpy.ramBanks()).thenReturn(resultRamBanks);
        when(gameSpy.getName()).thenReturn(romFileName);
        final Ram ramMock = mock(Ram.class);
        when(ramMock.getObject()).thenReturn(data);
        final CompletableFuture<ByteBuffer> byteBufferCompletableFuture = battery.saveRam(gameSpy, ramMock);
        final ByteBuffer byteBuffer = byteBufferCompletableFuture.get();
        assertTrue("Not same testSaveRam", byteBuffer.toString().equals(result));
    }

    public void testLoadRam() throws Exception {
        final String result = "java.nio.HeapByteBuffer[pos=0 lim=8192 cap=8192]";
        final byte[] data = new byte[0x4000];
        final Rom rom = new Rom(data);
        final String romFileName = "test.gb";
        GameImpl game = new GameImpl(rom, romFileName);
        GameImpl gameSpy = spy(game);
        final int resultRamBanks = 1;
        when(gameSpy.ramBanks()).thenReturn(resultRamBanks);
        when(gameSpy.getName()).thenReturn(romFileName);

        final CompletableFuture<ByteBuffer> byteBufferCompletableFuture = battery.loadRam(gameSpy);
        final ByteBuffer byteBuffer = byteBufferCompletableFuture.get();
        assertTrue("Not same testSaveRam", byteBuffer.toString().equals(result));
    }

    public void testLoadRamMoreRamBank() throws Exception {
        final String result = "java.nio.HeapByteBuffer[pos=0 lim=16384 cap=16384]";
        final byte[] data = new byte[0x4000];
        final Rom rom = new Rom(data);
        final String romFileName = "test.gb";
        GameImpl game = new GameImpl(rom, romFileName);
        GameImpl gameSpy = spy(game);
        final int resultRamBanks = 2;
        when(gameSpy.ramBanks()).thenReturn(resultRamBanks);
        when(gameSpy.getName()).thenReturn(romFileName);

        final CompletableFuture<ByteBuffer> byteBufferCompletableFuture = battery.loadRam(gameSpy);
        final ByteBuffer byteBuffer = byteBufferCompletableFuture.get();
        assertTrue("Not same testLoadRamMoreRamBank", byteBuffer.toString().equals(result));
    }
}