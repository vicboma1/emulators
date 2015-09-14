package emulator.framework.executor.deferred.resultAsync;

import junit.framework.TestCase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ResultAsyncImplTest extends TestCase {


    public static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    private ResultAsyncImpl<Callable<Boolean>,ExecutorService> resultAsync;

    public void setUp() throws Exception {
        super.setUp();
        this.resultAsync = new ResultAsyncImpl(EXECUTOR_SERVICE,new Callable(){
            public Boolean call() throws Exception{
                return true;
            }
        });
    }

    public void tearDown() throws Exception {
        this.resultAsync = null;
    }

    public void testGetExecutor() throws Exception {
        final ExecutorService executor = this.resultAsync.getExecutor();
        assertSame("Not same getExecutor", executor, EXECUTOR_SERVICE);
    }

    public void testGetResult() throws Exception {
        Callable<Boolean> result = this.resultAsync.getResult();
        Boolean call = result.call();
        assertTrue("Not same result",call);
    }
}