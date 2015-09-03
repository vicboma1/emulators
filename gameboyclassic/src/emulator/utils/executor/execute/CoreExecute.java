package emulator.utils.executor.execute;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Created by vicboma on 03/09/15.
 */
public interface CoreExecute {
    <T> CompletableFuture addCallableAsync(Callable<T> Callable);

    <T> CompletableFuture addRunnableAsync(Runnable addRunnableAsync);

    <T> CompletableFuture<T> addSupplier(Supplier<T> supplier);

    Integer size();
}
