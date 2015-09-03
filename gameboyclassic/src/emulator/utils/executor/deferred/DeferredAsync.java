package emulator.utils.executor.deferred;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 03/09/15.
 */
public interface DeferredAsync<T> {

    CompletableFuture<T> getCompletableFuture();
    Callable<T> getCallable();
    Runnable getRunnable();

}
