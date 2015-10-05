package emulator.toolBar.others;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.pattern.action.ActionInjector;
import toolBar.others.action.ToolBarOthersItemAction;
import toolBar.others.action.ToolBarOthersItemActionImpl;
import toolBar.others.item.ToolBarOthersItemMenu;
import toolBar.others.item.ToolBarOthersItemMenuImpl;
import utils.shell.Logger;
import utils.toolBar.ToolBarUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.Map;

/**
 * Created by vicboma on 31/08/15.
 */
public class ToolBarOthersImpl implements ToolBarOthers {
    public static final String ACTION_PERFORMED_OHTERS_TOOL_BAR_ASYNC = "Action Performed Others Tool Bar Async: ";
    public static final String COMMAND_NOT_FOUND_IN_OTHERS_TOOL_BAR = "Command not found in Others Tool Bar: ";

    private Menu others;
    private Menu menuDisassembler;
    private Map<String, ActionInjector> mapOthers;
    private java.util.List<MenuItem> menuItemOthers;
    private java.util.List<CheckboxMenuItem> menuItemDisassembler;


    @Inject
    public framework.logger.api.Logger logger;
    @Inject
    public ToolBarUtils toolBarUtils;
    @Inject
    public ToolBarOthersItemAction toolBarOthersItemAction;
    @Inject
    public ToolBarOthersItemMenu toolBarOthersItemMenu;

    public ToolBarOthersImpl() {
    }

    @PostConstruct
    public void initialize() {
        logger.info(this);
        this.mapOthers = toolBarOthersItemAction.load();
        this.menuItemDisassembler = toolBarOthersItemMenu.loadDisassembler(this);
        menuItemOthers = toolBarOthersItemMenu.loadMenu(this);
        others = toolBarUtils.createMenu(ToolBarOthersItemActionImpl.OTHERS, menuItemOthers);
        menuDisassembler = toolBarUtils.createMenu(ToolBarOthersItemActionImpl.DISASSEMBLER, menuItemDisassembler);
        others.add(menuDisassembler);
    }

    public Menu getMenu() {
        return this.others;
    }

    public CheckboxMenuItem getOthersConsole() {
        return (CheckboxMenuItem) this.others.getItem(ToolBarOthersItemMenuImpl.INDEX_CONSOLE);
    }

    public CheckboxMenuItem getItemDisassembler(int index) {
        return (CheckboxMenuItem)this.menuDisassembler.getItem(index);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String command = e.getItem().toString();
        commander(command);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand().toString();
        commander(command);
    }

    private void commander(String command) {
        final Boolean commander = toolBarUtils.commander(command, mapOthers, ACTION_PERFORMED_OHTERS_TOOL_BAR_ASYNC);
        if (!commander)
            Logger.Error(COMMAND_NOT_FOUND_IN_OTHERS_TOOL_BAR + command);
    }


}
