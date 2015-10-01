package emulator.framework.context.src;

import framework.context.api.FrameworkConfig;
import framework.context.api.FrameworkContext;
import framework.context.api.FrameworkExtension;
import framework.executor.ThreadPool;
import framework.injector.api.type.Injector;
import framework.injector.src.InjectorImpl;
import framework.logger.api.Logger;
import framework.logger.src.LoggerImpl;
import utils.WorkerThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 27/02/14
 * Time: 01:47
 * To change this template use File | Settings | File Templates.
 */
public class FrameworkContextImpl implements FrameworkContext {
    public static final String FRAMEWORK_CONTEXT_INITIALIZE = "FrameworkContext initialize";
    private Injector injector;
    private Logger logger;
    private List<CompletableFuture> completableAsyncList;

    public static FrameworkContext create() throws Exception {
        return new FrameworkContextImpl();
    }

    public FrameworkContextImpl() throws Exception {
        try {
            logger = LoggerImpl.create();
            completableAsyncList = new ArrayList();
            InjectorImpl.check();
            injector = InjectorImpl.create();
        } catch (Exception e) {
            logger.error(this, FRAMEWORK_CONTEXT_INITIALIZE, e);
        } finally {
            injector.mapValue(Injector.class, injector);
            injector.mapValue(FrameworkContext.class, this);
            injector.mapValue(Logger.class, logger);
            injector.mapSingleton(WorkerThreadFactory.class, WorkerThreadFactory.class);
            injector.mapSingleton(ThreadPool.class, ThreadPool.class);
        }

    }

    @Override
    public FrameworkContext immediatelyInstallExtension(FrameworkExtension frameworkExtension) throws Exception {
        completableAsyncList.add(new CompletableFuture().runAsync(() -> {
            try {
                injector.injectInto(frameworkExtension);
                frameworkExtension.installExtensionAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
                }));

        return this;
    }

    @Override
    public FrameworkContext installExtensionAsync(FrameworkExtension frameworkExtension) throws Exception {
        completableAsyncList.add(new CompletableFuture().runAsync(() -> {
            try {
                injector.injectInto(frameworkExtension);
                frameworkExtension.<FrameworkContext>installExtensionAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        return this;
    }

    @Override
    public FrameworkContext immediatelyConfigure(FrameworkConfig frameworkConfig) throws Exception {
        completableAsyncList.add(new CompletableFuture().runAsync(() -> {
            try {
                injector.injectInto(frameworkConfig);
                frameworkConfig.configure();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        return this;
    }

    @Override
    public FrameworkContext configureAsync(FrameworkConfig frameworkConfig) throws Exception {
        completableAsyncList.add(new CompletableFuture().runAsync(() -> {
            try {
                injector.injectInto(frameworkConfig);
                frameworkConfig.configure();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        return this;
    }




    //Thread Pools

    @Override
    public <T> FrameworkContext executeQueueAsync(Callable<T> callable) throws Exception {
        injector.getInstance(ThreadPool.class).get().addCallableAsync(callable);
        return this;
    }

    @Override
    public FrameworkContext executeQueueAsync(Runnable runnable) throws Exception {
        injector.getInstance(ThreadPool.class).get().addRunnableAsync(runnable);
        return this;
    }

    @Override
    public <T> CompletableFuture<T> executeCompletableAsync(Callable<T> callable) throws Exception {
        return injector.getInstance(ThreadPool.class).get().addCallableAsync(callable);
    }

    @Override
    public CompletableFuture executeCompletableAsync(Runnable runnable) throws Exception {
        return injector.getInstance(ThreadPool.class).get().addRunnableAsync(runnable);
    }



    @Override
    public Injector injector() {
        return this.injector;
    }

    @Override
    public CompletableFuture<FrameworkContext> persistenConfiguration() {
        completableAsyncList
                .stream()
                .forEach(x -> x.join());

        return CompletableFuture.completedFuture(this);
    }
}
