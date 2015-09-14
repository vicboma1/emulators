package emulator.framework.executor.execute;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class CoreExecuteImplTest extends TestCase {

    private CoreExecuteImpl coreExecute;

    public void setUp() throws Exception {
        super.setUp();
        final ThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        coreExecute = new CoreExecuteImpl(new ConcurrentLinkedQueue(),threadPoolExecutor);
    }

    public void tearDown() throws Exception {
        coreExecute = null;
    }

    public void testAddCallableAsyncTrue() throws Exception {
        CompletableFuture<Boolean> booleanCompletableFuture = coreExecute.addCallableAsync(() -> true);
        assertTrue("Not same addCallableAsync true", booleanCompletableFuture.get());
    }

    public void testAddCallableAsyncFalse() throws Exception {
        CompletableFuture<Boolean> booleanCompletableFuture = coreExecute.addCallableAsync(() -> false);
        assertFalse("Not same addCallableAsync false ", booleanCompletableFuture.get());
    }


    public void testAddCallableAsync() throws Exception {
        final List<Boolean> list = Arrays.asList(true, false, true, false, true, false, true, false, true, false, true, false, true, false, true, false);
        IntStream.range(0, list.size()).boxed().forEach(x -> {
            CompletableFuture<Boolean> booleanCompletableFuture = coreExecute.addCallableAsync(() -> list.get(x));
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
            coreExecute.addRunnableAsync(() -> {
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
            CompletableFuture<Boolean> booleanCompletableFuture = coreExecute.addSupplier(() -> list.get(x));
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

    public void testSize() throws Exception {
        final List<Boolean> list = Arrays.asList(true,false,true,false,true,false,true,false,true,false,true,false,true,false,true,false);
        assertTrue("Not same size", coreExecute.size() == 0);
        IntStream.range(0,list.size()).boxed().forEach(x -> {
            coreExecute.addCallableAsync(() -> list.get(x));
        });
        assertTrue("Not same size", coreExecute.size() == list.size());

        try {
            Thread.sleep(1000);
            assertTrue("Not same size", coreExecute.size() == 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}