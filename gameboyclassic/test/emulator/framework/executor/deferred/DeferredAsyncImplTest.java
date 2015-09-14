package emulator.framework.executor.deferred;

import junit.framework.TestCase;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class DeferredAsyncImplTest extends TestCase {


    public void testGetCompletableFuture() throws Exception {
        final  DeferredAsyncImpl deferredAsync = new DeferredAsyncImpl(()->{} );
        DeferredAsyncImpl spy = spy(deferredAsync);
        spy.getCompletableFuture();
        verify(spy).getCompletableFuture();
    }

    public void testGetCallable() throws Exception {
        final  DeferredAsyncImpl deferredAsync = new DeferredAsyncImpl(() -> true);
        DeferredAsyncImpl spy = spy(deferredAsync);
        spy.getCallable();
        verify(spy).getCallable();
    }

    public void testGetRunnable() throws Exception {
        final  DeferredAsyncImpl deferredAsync = new DeferredAsyncImpl(()->{} );
        DeferredAsyncImpl spy = spy(deferredAsync);
        spy.getRunnable();
        verify(spy).getRunnable();
    }
}