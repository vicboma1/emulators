package emulator.utils.toolBar;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.injector.api.type.Injector;
import framework.logger.api.Logger;
import framework.pattern.action.ActionInjector;
import toolBar.others.action.ToolBarOthersItemAction;
import toolBar.others.action.ToolBarOthersItemActionImpl;
import utils.mainLoop.MainLoopUtils;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by vicboma on 30/09/15.
 */
public class ToolBarUtilsImpl implements ToolBarUtils {

    @Inject
    public Logger logger;
    @Inject
    public Injector injector;

    public ToolBarUtilsImpl() {
    }

    @PostConstruct
    public void initialize(){
        logger.info(this);
    }

    /**
     * Optimizado para cualquier tipo de menu item (check / radio / normal ...)
     * @param menuName
     * @param menuItemList
     * @param <T>
     * @return
     */
    @Override
    public <T extends MenuItem> Menu createMenu(String menuName, List<T> menuItemList){
        final Menu menu = injector.getInstance(Menu.class);

        menu.setLabel(menuName);
        menuItemList.stream().forEach(x -> {
            if (x == null)
                menu.addSeparator();
            else
                menu.add(x);
        });

        return menu;
    }

    @Override
    public Menu addSubMenu(Menu menu, List<MenuItem> menuItemList){
        menuItemList.stream().forEach(x -> {
            if (x == null)
                menu.addSeparator();
            else
                menu.add(x);
        });

        return menu;
    }


    public void logError(String command, String message) {
        logger.error(this, message + command);
    }

    public Boolean commander(String command, Map<String,ActionInjector> map, String message) {
        final ActionInjector toolBarFileAction = map.get(command);
        if (toolBarFileAction != null) {
            try {
                logger.info(this, message +command);
                    toolBarFileAction.execute(injector);
            } catch (Exception ex) {
                logger.error(this, ex.getMessage(), ex);
            }
            return true;
        }
        return false;
    }

    //
    public void addOptionAsync(Injector injector, String openRom) {
        synchronized (injector) {
            final MainLoopUtils mainLoopUtils = injector.getInstance(MainLoopUtils.class);
            mainLoopUtils.addOptionAsync(injector, openRom);
        }
    }

    public ToolBarOthersItemActionImpl getToolBarOthersItemActionImpl(Injector injector) {
        return (ToolBarOthersItemActionImpl) injector.getInstance(ToolBarOthersItemAction.class);
    }
}
