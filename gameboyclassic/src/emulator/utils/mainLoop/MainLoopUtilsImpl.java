package emulator.utils.mainLoop;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.injector.api.type.Injector;
import framework.logger.api.Logger;
import main.loop.MainLoopCore;

/**
 * Created by vicboma on 30/09/15.
 */
public class MainLoopUtilsImpl implements MainLoopUtils {

    @Inject
    public Logger logger;

    public MainLoopUtilsImpl() {
    }

    @PostConstruct
    public void initialize(){
        logger.info(this);
    }

    @Override
    public void addOptionAsync(Injector injector, String opc){
        synchronized (this){
            optionAsync(injector,opc);
        }

    }
    private void optionAsync(Injector injector, String openRom) {
        final MainLoopCore mainLoopCoreImpl = getInstanceMainLoopCore(injector);
        mainLoopCoreImpl.queue(openRom);
    }

    private MainLoopCore getInstanceMainLoopCore(Injector injector) {
        return injector.getInstance(MainLoopCore.class);
    }
}
