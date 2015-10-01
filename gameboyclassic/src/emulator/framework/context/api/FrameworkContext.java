package emulator.framework.context.api;

import framework.injector.api.Injector;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 26/02/14
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public interface FrameworkContext
{
	 //Methods channeables
	 FrameworkContext immediatelyConfigure(FrameworkConfig frameworkConfig) throws Exception;
	 FrameworkContext queueConfigure(FrameworkConfig frameworkConfig) throws Exception;
	 FrameworkContext installExtensionAsync(FrameworkExtension frameworkExtension) throws Exception;
	 FrameworkContext immediatelyInstallExtension(FrameworkExtension frameworkExtension) throws Exception;

	 <T> CompletableFuture<T>  executeCompletableAsync(Callable<T> callable) throws Exception;   //then(....)
	 <T> FrameworkContext executeQueueAsync(Callable<T> callable) throws Exception;

	 CompletableFuture executeCompletableAsync(Runnable runnable) throws Exception;   //then(....)
	 FrameworkContext executeQueueAsync(Runnable runnable) throws Exception;

	 Injector injector();
	 FrameworkContext persistenConfiguration();
	 FrameworkContext endConfiguration(Boolean state);
}
