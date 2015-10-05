package emulator.toolBar.others.item;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.logger.api.Logger;
import toolBar.others.ToolBarOthers;
import toolBar.others.action.ToolBarOthersItemActionImpl;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by vicboma on 30/09/15.
 */
public class ToolBarOthersItemMenuImpl implements ToolBarOthersItemMenu {
    public static final int INDEX_CONSOLE = 0;
    //SUBMENU
    public static final int INDEX_DECIMAL = 1;
    public static final int INDEX_HEXADECIMAL = 0;

    @Inject
    public Logger logger;

    public ToolBarOthersItemMenuImpl() {

    }

    @PostConstruct
    public void initialize(){
        logger.info(this);
    }


    @Override
    public java.util.List<MenuItem> loadMenu(ToolBarOthers toolBarOthers) {
        return new ArrayList() {
            {
                add(INDEX_CONSOLE, new CheckboxMenuItem() {
                    {
                        setLabel(ToolBarOthersItemActionImpl.CONSOLE);
                        setActionCommand(ToolBarOthersItemActionImpl.CONSOLE);
                        addItemListener(toolBarOthers);
                        setState(true);
                    }
                });
            }
        };
    }

    @Override
    public java.util.List<CheckboxMenuItem> loadDisassembler(ToolBarOthers toolBarOthers) {
        return new ArrayList() {
            {
                add(INDEX_HEXADECIMAL, new CheckboxMenuItem() {
                    {
                        setLabel(ToolBarOthersItemActionImpl.HEXADECIMAL);
                        setActionCommand(ToolBarOthersItemActionImpl.HEXADECIMAL);
                        addItemListener(toolBarOthers);
                    }
                });
                add(INDEX_DECIMAL, new CheckboxMenuItem() {
                    {
                        setLabel(ToolBarOthersItemActionImpl.DECIMAL);
                        setActionCommand(ToolBarOthersItemActionImpl.DECIMAL);
                        addItemListener(toolBarOthers);
                    }
                });
            }
        };
    }
}
