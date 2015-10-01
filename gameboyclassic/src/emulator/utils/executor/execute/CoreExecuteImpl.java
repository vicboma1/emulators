package emulator.utils.executor.execute;


import emulator.utils.executor.deferred.DeferredAsync;
import emulator.utils.executor.deferred.DeferredAsyncImpl;
import emulator.utils.shell.Logger;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Created by vicboma on 16/06/14.
 */
public class CoreExecuteImpl implements CoreExecute {

    public static final int DELAY = 0;
    public static final int PERIOD = 10;
    public static final String THREAD_POOL_PRIORITY_QUEUE_ASYNC = "ThreadPool - PriorityQueueAsync";

    private Timer timer;
    private ThreadPoolExecutor threadPoolExecutor;
    private Queue<DeferredAsync> priorityQueue;
    private int resolved = 0;

    public CoreExecuteImpl(Queue<DeferredAsync> _priorityQueue, ThreadPoolExecutor _threadPoolExecutor) {
        this.priorityQueue = _priorityQueue;
        this.threadPoolExecutor = _threadPoolExecutor;
        this.timer = new Timer(THREAD_POOL_PRIORITY_QUEUE_ASYNC, true);
        this.timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        final Queue<DeferredAsync> _priorityQueue = priorityQueue;
                        if (_priorityQueue.isEmpty()) {
                            sleep(10);
                            return;
                        }
                        _priorityQueue
                                .parallelStream()
                                .filter(x -> x != null)
                                .forEach(element -> {
                                            try {
                                                Future submit = null;

                                                _priorityQueue.remove(element);
                                                final Callable callable = element.getCallable();
                                                final Runnable runnable = element.getRunnable();
                                                final CompletableFuture completableFuture = element.getCompletableFuture();

                                                submit  = (callable == null)
                                                        ? threadPoolExecutor.submit(runnable)
                                                        : threadPoolExecutor.submit(callable);

                                                try {
                                                    completableFuture.complete(submit.get());
                                                }
                                                catch (ExecutionException | InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            } catch (RejectedExecutionException e) {
                                                Logger.Error("Taks Async Rejected " + (++resolved));
                                                _priorityQueue.add(element);
                                            } finally {
                                                Logger.DebugValidate("Taks Async Resolved " + (++resolved));
                                                sleep(5);
                                            }
                                        }
                                );
                    }

                    private void sleep(Integer time) {
                        try {
                            Thread.sleep(time);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },
                DELAY,
                PERIOD);
    }

    @Override
    public <T> CompletableFuture addCallableAsync(Callable<T> Callable) {
        final DeferredAsync<T> promiseAsync = new DeferredAsyncImpl(Callable);
        priorityQueue.add(promiseAsync);
        return promiseAsync.getCompletableFuture();
    }

    @Override
    public <T> CompletableFuture addRunnableAsync(Runnable addRunnableAsync) {
        final DeferredAsync<T> promiseAsync = new DeferredAsyncImpl(addRunnableAsync);
        priorityQueue.add(promiseAsync);
        return promiseAsync.getCompletableFuture();
    }

    @Override
    public <T> CompletableFuture<T> addSupplier(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier,threadPoolExecutor);
    }

    @Override
    public Integer size() {
        return priorityQueue.size();
    }
}