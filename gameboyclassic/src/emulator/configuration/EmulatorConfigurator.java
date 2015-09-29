package emulator.configuration;

import configuration.config.*;
import configuration.extension.CartridgeExtension;
import configuration.extension.GameBoyDMGExtension;
import framework.context.api.FrameworkContext;
import framework.context.src.FrameworkContextImpl;

/**
 * Created by vicboma on 28/09/15.
 */
public class EmulatorConfigurator {

    private static FrameworkContext context;

    public static EmulatorConfigurator configure() throws Exception {
        return new EmulatorConfigurator();
    }

    public EmulatorConfigurator() throws Exception {

        context = FrameworkContextImpl.create();

        context
                .immediatelyInstallExtension(GameBoyDMGExtension.create())
                .immediatelyInstallExtension(CartridgeExtension.create())
                .installExtensionAsync(MainLoopCoreConfigure.create())
                .immediatelyConfigure(GraphicsConfigure.create())
                .immediatelyConfigure(MainWindowConfigure.create())
                .immediatelyConfigure(ConsoleConfigure.create())
                .immediatelyConfigure(ToolBarWindowConfigure.create())
                .executeCompletableAsync(() -> {
                    return configuration();
                });

        context.persistenConfiguration();
    }
}
