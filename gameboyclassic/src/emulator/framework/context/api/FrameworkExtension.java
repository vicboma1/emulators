package emulator.framework.context.api;

import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 26/02/14
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */
public interface FrameworkExtension
{
	<T> CompletableFuture<T> installExtensionAsync() throws Exception;
}
