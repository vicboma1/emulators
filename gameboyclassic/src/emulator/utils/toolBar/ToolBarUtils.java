package emulator.utils.toolBar;

import framework.injector.api.type.Injector;
import framework.pattern.action.ActionInjector;
import toolBar.others.action.ToolBarOthersItemActionImpl;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by vicboma on 30/09/15.
 */
public interface ToolBarUtils {
    <T extends MenuItem> Menu createMenu(String menuName, List<T> menuItemList);
    Menu addSubMenu(Menu menu, List<MenuItem> menuItemList);
    void logError(String command, String message);
    Boolean commander(String command, Map<String, ActionInjector> map, String message);
    void addOptionAsync(Injector injector, String openRom);
    ToolBarOthersItemActionImpl getToolBarOthersItemActionImpl(Injector injector);
}
