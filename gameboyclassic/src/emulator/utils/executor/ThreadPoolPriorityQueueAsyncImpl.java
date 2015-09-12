package emulator.utils.executor;

import utils.executor.execute.CoreExecute;
import utils.executor.execute.CoreExecuteImpl;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Created by vicboma on 06/06/14.
 */

/**
 * Created by vicboma on 16/06/14.
 */
public class ThreadPoolPriorityQueueAsyncImpl implements ThreadPoolPriorityQueueAsync {

    public static final int AVAIBLE_THREADS = Runtime.getRuntime().availableProcessors();
    public static final int CORE_POOL_SIZE = (AVAIBLE_THREADS <= 2) ? (AVAIBLE_THREADS / 2) : 1;
    public static final int MAXIMUM_POOL_SIZE = (AVAIBLE_THREADS <= 2) ? (AVAIBLE_THREADS - 1) : 1;
    public static final int KEEP_ALIVE = 10;
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private ThreadPoolExecutor threadPoolExecutor;
    private CoreExecute coreExecute;

    public static ThreadPoolPriorityQueueAsync Create(int corePoolSize, int maximumPoolSize, int keepAliveTime, TimeUnit milliseconds, LinkedBlockingQueue<Runnable> runnables, ThreadFactory threadFactory) {
        return new ThreadPoolPriorityQueueAsyncImpl(
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, milliseconds, runnables, threadFactory));
    }

    ThreadPoolPriorityQueueAsyncImpl(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
        this.coreExecute = new CoreExecuteImpl(new ConcurrentLinkedQueue(), this.threadPoolExecutor);

    }

    @Override
    public <T> CompletableFuture<T> addCallableAsync(Callable<T> callable) {
        return coreExecute.addCallableAsync(callable);
    }

    @Override
    public <T> CompletableFuture<T> addRunnableAsync(Runnable runnableFuture) {
        return coreExecute.addRunnableAsync(runnableFuture);
    }


    @Override
    public <T> CompletableFuture<T> addSupplier(Supplier<T> supplier) {
        return coreExecute.addSupplier(supplier);
    }

    @Override
    public Executor executor() {
        return threadPoolExecutor;
    }

    @Override
    public Integer size() {
        return this.coreExecute.size();
    }
}