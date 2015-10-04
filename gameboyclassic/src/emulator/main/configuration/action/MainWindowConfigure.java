package emulator.configuration.action;

import framework.context.annotation.FrameworkInstall;
import framework.context.api.FrameworkConfig;
import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.injector.api.type.Injector;
import framework.install._enum.OrderInstall;
import framework.install.annotation.PriorityInstall;
import framework.logger.api.Logger;
import screen.Screen;
import screen.ScreenImpl;
import screen.componentListener.ScreenComponentListener;
import screen.componentListener.ScreenComponentListenerImpl;
import screen.windowListener.ScreenWindowListener;
import screen.windowListener.ScreenWindowListenerImpl;
import toolBar.controls.GamePadController;
import utils.shell.console.ConsoleWindowsListener;

import java.awt.*;


/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 06/02/14
 * Time: 16:33
 * To change this template use File | Settings | File Templates.
 */
@PriorityInstall(order = OrderInstall.LOW)
public class MainWindowConfigure implements FrameworkConfig
{
	public static final String ASYNCHRONOUS_DEBUG_CONSOLE = "Asynchronous Debug Console";

	private Window window;
	private Dialog dialog;
	private Screen screen;

	@Inject
	public Injector injector;
	@Inject
	public Logger logger;

	public static FrameworkConfig create(){
		return new MainWindowConfigure();
	}


	private static Window initializeWindow() {
		return new Window();
	}

	private static Dialog initializeDialog(Screen screen){
		return new Dialog((Frame) screen, ASYNCHRONOUS_DEBUG_CONSOLE, false);
	}

	public MainWindowConfigure()
	{
		screen = new ScreenImpl();
		window = initializeWindow();
		dialog = initializeDialog(screen);

	}

	@PostConstruct
	public void initialize(){
		logger.info(this);
	}

	@Override
	@FrameworkInstall
	public void configure() throws Exception
	{
		mapperClasses();
		injectInto();
	}

	private void mapperClasses() {
		injector.mapValue(Screen.class, screen);
		//injector.mapSingleton(Screen.class, ScreenImpl.class);
		injector.mapValue(Window.class, window);
		injector.mapValue(Dialog.class, dialog);
		injector.mapSingleton(ScreenComponentListener.class, ScreenComponentListenerImpl.class);
		injector.mapSingleton(ConsoleWindowsListener.class, ConsoleWindowsListener.class);
		injector.mapSingleton(GamePadController.class, GamePadController.class);
		injector.mapSingleton(ScreenWindowListener.class, ScreenWindowListenerImpl.class);
	}

	private void injectInto() throws Exception {
		injector.injectInto(screen);
		injector.injectInto(window);
	}


}
