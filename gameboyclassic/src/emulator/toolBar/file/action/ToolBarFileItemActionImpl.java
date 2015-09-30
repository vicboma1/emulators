package emulator.toolBar.file.action;

import cartridge.CartridgeAsync;
import cpu.processor.z80.Z80Impl;
import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.injector.api.type.Injector;
import framework.logger.api.Logger;
import main.loop.LoopCoreImpl;
import toolBar.file.ToolBarFileImpl;
import utils.MainLoopUtils;
import utils.window.Window;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 30/09/15.
 */
public class ToolBarFileItemActionImpl implements ToolBarFileItemAction {

    public static final String ROM_NOT_LOADED = "ROM not loaded.";
    public static final String TRY_AGAIN_TO_LOAD_ROM_PLEASE = "Try again to load rom please.";
    @Inject
    public Logger logger;

    public ToolBarFileItemActionImpl() {
    }

    @PostConstruct
    public void initialize(){
        logger.info(this);
    }

    //command mapper pattern
    @Override
    public Map<String, ToolBarFileAction> load() {
        return new Hashtable() {
            {
                put(ToolBarFileImpl.OPEN_ROM, (ToolBarFileAction) (injector) -> {
                    pauseEmulator(injector);
                    addOptionAsync(injector, ToolBarFileImpl.OPEN_ROM);
                });

                put(ToolBarFileImpl.EMULATE, (ToolBarFileAction) (injector) -> {
                    final CartridgeAsync cartridgeAsync = injector.getInstance(CartridgeAsync.class);
                    if (cartridgeAsync != null) {
                        pauseEmulator(injector);
                        addOptionAsync(injector,ToolBarFileImpl.EMULATE);
                    } else
                        injector.getInstance(Window.class).showDialogError(ROM_NOT_LOADED, TRY_AGAIN_TO_LOAD_ROM_PLEASE);

                });

                put(ToolBarFileImpl.PAUSE, (ToolBarFileAction) (injector) -> {
                    final Z80Impl z80 = injector.getInstance(Z80Impl.class);
                    if(z80!=null)
                        z80.isPause = !z80.isPause;
                });

                put(ToolBarFileImpl.RESET, (ToolBarFileAction) (injector) -> {
                    pauseEmulator(injector);
                    addOptionAsync(injector, ToolBarFileImpl.RESET);
                });

                put(ToolBarFileImpl.EXIT, (ToolBarFileAction) (injector) -> {
                    pauseEmulator(injector);;
                    LoopCoreImpl.threadCoreRunning = false;
                    Thread.yield();
                    addOptionAsync(injector,ToolBarFileImpl.EXIT);
                });
            }

            private void pauseEmulator(Injector injector) {
                final Z80Impl z80 = injector.getInstance(Z80Impl.class);
                if(z80!=null)
                    z80.isStop = true;
            }

            private void addOptionAsync(Injector injector, String openRom) {
                final MainLoopUtils mainLoopUtils = injector.getInstance(MainLoopUtils.class);
                mainLoopUtils.addOptionAsync(injector, openRom);
            }
        };
    }
}
