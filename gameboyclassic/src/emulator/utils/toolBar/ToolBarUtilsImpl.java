package emulator.utils.toolBar;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.injector.api.type.Injector;
import framework.logger.api.Logger;

import java.awt.*;
import java.util.List;

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

    @Override
    public Menu createMenu(String menuName, List<MenuItem> menuItemList){
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

}
