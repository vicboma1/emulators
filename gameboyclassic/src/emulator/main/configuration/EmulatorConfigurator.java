package main.configuration;

import main.configuration.powerUp.ConfiguratorAction;
import main.configuration.powerUp.EmulatorConfiguratorAction;
import main.configuration.config.ConsoleConfigure;
import main.configuration.config.MainWindowConfigure;
import main.configuration.config.ToolBarWindowConfigure;
import main.configuration.extension.CartridgeExtension;
import main.configuration.extension.GraphicsConfigure;
import main.configuration.extension.ProcessorConfigure;
import main.configuration.extension.SoundMixerConfigure;
import main.configuration.queue.MainLoopCoreConfigure;
import framework.context.api.FrameworkContext;
import framework.context.src.FrameworkContextImpl;
import framework.injector.api.annotation.Inject;
import framework.injector.api.type.Injector;
import main.GameBoy;
import main.loopCore.LoopCore;
import screen.Screen;
import toolBar.ToolBar;

import java.awt.*;
import java.util.Map;

/**
 * Created by vicboma on 28/09/15.
 */
public class EmulatorConfigurator {

    private static FrameworkContext context;

    @Inject
    public Injector injector;
    @Inject
    public Screen screen;
    @Inject
    public ToolBar toolBar;
    @Inject
    public Dialog dialog;

    public static EmulatorConfigurator configure() throws Exception {
        return new EmulatorConfigurator();
    }

    public EmulatorConfigurator() throws Exception {

        context = FrameworkContextImpl.create();
        context
                .immediatelyInstallExtension(CartridgeExtension.create())
                .immediatelyInstallExtension(GraphicsConfigure.create())
                .immediatelyInstallExtension(SoundMixerConfigure.create())
                .immediatelyInstallExtension(ProcessorConfigure.create())
                .installExtensionAsync(MainLoopCoreConfigure.create())
                .configureAsync(MainWindowConfigure.create())
                .configureAsync(ConsoleConfigure.create())
                .configureAsync(ToolBarWindowConfigure.create())
                .executeQueueAsync(() -> { configurationComplete(); });

        context.persistentConfiguration()
                .thenAcceptAsync(context -> {
                    context
                            .injector()
                            .getInstance(LoopCore.class)
                            .executeAsync();
                });
    }

    private void configurationComplete() {
        try {
            final Injector injector = context.injector();
            injector.injectInto(this);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            final EmulatorConfiguratorAction emulatorConfiguratorAction = EmulatorConfiguratorAction.create();
            final Map<Boolean, ConfiguratorAction> initialize = emulatorConfiguratorAction.initialize();
            final ConfiguratorAction action = initialize.get(GameBoy.DEBUG);
            action.execute(screen,toolBar,dialog);
        }
    }


}
