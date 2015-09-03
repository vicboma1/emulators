package emulator.utils.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 * Created by vicboma on 03/09/15.
 */
public interface ThreadPoolPriorityQueueAsync {
    <T> CompletableFuture<T> addCallableAsync(Callable<T> callable);

    <T> CompletableFuture<T> addRunnableAsync(Runnable runnableFuture);

    <T> CompletableFuture<T> addSupplier(Supplier<T> supplier);

    Executor executor();

    Integer size();
}
