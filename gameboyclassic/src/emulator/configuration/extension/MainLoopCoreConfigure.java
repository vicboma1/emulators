package emulator.configuration.extension;

import framework.context.annotation.FrameworkInstall;
import framework.context.api.FrameworkExtension;
import framework.executor.ThreadPool;
import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.injector.api.type.Injector;
import framework.install._enum.OrderInstall;
import framework.install.annotation.PriorityInstall;
import framework.logger.api.Logger;
import main.loop.LoopCore;
import main.loop.LoopCoreImpl;
import main.loop.MainLoopCore;
import main.loop.MainLoopCoreImpl;
import utils.mainLoop.MainLoopUtils;
import utils.mainLoop.MainLoopUtilsImpl;

import java.util.concurrent.CompletableFuture;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 06/02/18
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
@PriorityInstall(order = OrderInstall.MEDIUM)
public class MainLoopCoreConfigure implements FrameworkExtension
{
	@Inject
	public Injector injector;
	@Inject
	public Logger logger;

	private MainLoopCore mainLoopCoreImpl;
	private LoopCore loopCoreImpl;

	public static FrameworkExtension create(){
		return new MainLoopCoreConfigure();
	}

	public MainLoopCoreConfigure()
	{
	}

	@PostConstruct
	public void initialize(){
		logger.info(this);
		mainLoopCoreImpl = initializeMainLoopCore();
		loopCoreImpl = initializeLoopCore();
	}

	private LoopCore initializeLoopCore() {
		return LoopCoreImpl.create();
	}

	private MainLoopCore initializeMainLoopCore() {
		return MainLoopCoreImpl.create();
	}

	@Override
	@FrameworkInstall
	public <T> CompletableFuture<T> installExtensionAsync() throws Exception {
		return injector.getInstance(ThreadPool.class).get().addCallableAsync(() -> {
			mapperClasses();
			injectInto();
			return (T) CompletableFuture.completedFuture(this);
		});
	}


	private void mapperClasses() {
		injector.mapSingleton(MainLoopUtils.class, MainLoopUtilsImpl.class);
		injector.mapValue(MainLoopCore.class, mainLoopCoreImpl);
		injector.mapValue(LoopCore.class, loopCoreImpl);
	}

	private void injectInto() throws Exception {
		injector.injectInto(mainLoopCoreImpl);
		injector.injectInto(loopCoreImpl);
	}
}
