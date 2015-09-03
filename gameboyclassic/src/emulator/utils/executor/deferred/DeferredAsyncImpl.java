package emulator.utils.executor.deferred;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Created by vicboma on 03/09/15.
 */
public class DeferredAsyncImpl<T> implements DeferredAsync<T> {

    private CompletableFuture<T> completableFuture;
    private Callable<T> callable;
    private Runnable runnableFuture;

    public DeferredAsyncImpl(Callable<T> callable) {
        this.completableFuture = new CompletableFuture();
        this.callable = callable;
    }

    public DeferredAsyncImpl(Runnable runnableFuture) {
        this.completableFuture = new CompletableFuture();
        this.runnableFuture = runnableFuture;
    }

    public CompletableFuture<T> getCompletableFuture() {
        return completableFuture;
    }

    public Callable<T> getCallable() {
        return callable;
    }
    public Runnable getRunnable() {
        return runnableFuture; }
}
