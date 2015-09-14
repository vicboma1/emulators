package emulator.framework.executor.threadPool;

import junit.framework.TestCase;
import utils.WorkerThreadFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class ThreadPoolPriorityQueueAsyncImplTest extends TestCase {

    private ThreadPoolPriorityQueueAsync threadPoolPriorityQueueAsync;

    public void setUp() throws Exception {
        super.setUp();
        threadPoolPriorityQueueAsync = ThreadPoolPriorityQueueAsyncImpl.Create(
                ThreadPoolPriorityQueueAsyncImpl.CORE_POOL_SIZE,
                ThreadPoolPriorityQueueAsyncImpl.MAXIMUM_POOL_SIZE,
                ThreadPoolPriorityQueueAsyncImpl.KEEP_ALIVE,
                ThreadPoolPriorityQueueAsyncImpl.TIME_UNIT,
                new LinkedBlockingQueue(),
                WorkerThreadFactory.Create());
    }

    public void tearDown() throws Exception {
        threadPoolPriorityQueueAsync = null;
    }

    public void testAddCallableAsyncTrue() throws Exception {
        CompletableFuture<Boolean> booleanCompletableFuture = threadPoolPriorityQueueAsync.addCallableAsync(() -> true);
        assertTrue("Not same addCallableAsync true", booleanCompletableFuture.get());
    }

    public void testAddCallableAsyncFalse() throws Exception {
        CompletableFuture<Boolean> booleanCompletableFuture = threadPoolPriorityQueueAsync.addCallableAsync(() -> false);
        assertFalse("Not same addCallableAsync false ", booleanCompletableFuture.get());
    }

    public void testAddCallableAsync() throws Exception {
        final List<Boolean> list = Arrays.asList(true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false);
        IntStream.range(0,list.size()).boxed().forEach(x -> {
            CompletableFuture<Boolean> booleanCompletableFuture = threadPoolPriorityQueueAsync.addCallableAsync(() -> list.get(x));
            try {
                Boolean result = booleanCompletableFuture.get();
                if(x%2==0)
                    assertTrue("Not same addCallableAsync", result);
                else
                    assertFalse("Not same addCallableAsync", result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }

    public void testAddRunnableAsync() throws Exception {
        final List<Boolean> list = Arrays.asList(true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false);
        IntStream.range(0,list.size()).boxed().forEach(x -> {
            CompletableFuture<Object> objectCompletableFutureDef = new CompletableFuture<Object>();
            threadPoolPriorityQueueAsync.addRunnableAsync(() -> {
                try {
                    Thread.sleep(200);
                    objectCompletableFutureDef.complete(new Object());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            try {
                Object result = objectCompletableFutureDef.get();
                assertNotNull("Not null",result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public void testAddSupplier() throws Exception {
        final List<Boolean> list = Arrays.asList(true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false);
        IntStream.range(0,list.size()).boxed().forEach(x -> {
            CompletableFuture<Boolean> booleanCompletableFuture = threadPoolPriorityQueueAsync.addSupplier(() -> list.get(x));
            try {
                Boolean result = booleanCompletableFuture.get();
                if(x%2==0)
                    assertTrue("Not same addCallableAsync", result);
                else
                    assertFalse("Not same addCallableAsync", result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public void testExecutor() throws Exception {
        CompletableFuture<Boolean> booleanCompletableFuture = new CompletableFuture();
        threadPoolPriorityQueueAsync.executor().execute(()->{
            try {
                Thread.sleep(200);
                booleanCompletableFuture.complete(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        assertTrue("Not same executor task",booleanCompletableFuture.get());
    }

    public void testSize() throws Exception {
        final List<Boolean> list = Arrays.asList(true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false);
        assertTrue("Not same size", threadPoolPriorityQueueAsync.size() == 0);
        IntStream.range(0,list.size()).boxed().forEach(x -> {
            threadPoolPriorityQueueAsync.addCallableAsync(() -> list.get(x));
        });
        assertTrue("Not same size", threadPoolPriorityQueueAsync.size() == list.size());

        try {
            Thread.sleep(1000);
            assertTrue("Not same size", threadPoolPriorityQueueAsync.size() == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}