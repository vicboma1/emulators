package emulator.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 05/08/15.
 */
public class FileAsync {

    public static CompletableFuture<ByteBuffer> read(String filePath, int total)  throws IOException, InterruptedException, ExecutionException {
        final CompletableFuture<ByteBuffer> completableFuture =  new CompletableFuture();
        final Path path = Paths.get(filePath);
        final AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        final ByteBuffer buffer = ByteBuffer.allocate(total);

        channel.read(buffer, 0, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                try { channel.close(); } catch (IOException e) {  e.printStackTrace(); }
                buffer.flip();
                completableFuture.complete(buffer);
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                completableFuture.completeExceptionally(exc);
            }
        });

        return completableFuture;
    }

    public static CompletableFuture<Boolean> write(String filePath, StringBuilder input)  throws IOException, InterruptedException, ExecutionException {
        final CompletableFuture<Boolean> completableFuture =  new CompletableFuture();

        byte [] byteArray = input.toString().getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);

        Path path = Paths.get(filePath);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        channel.read(buffer, 0, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                try { channel.close(); } catch (IOException e) {  e.printStackTrace(); }
                completableFuture.complete(Boolean.TRUE);
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                completableFuture.completeExceptionally(exc);
            }
        });

        return completableFuture;
    }

}

