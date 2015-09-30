package emulator.toolBar.file;

import framework.context.api.FrameworkContext;
import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.injector.api.type.Injector;
import toolBar.file.action.ToolBarFileAction;
import toolBar.file.action.ToolBarFileItemAction;
import toolBar.file.item.ToolBarFileItemMenu;
import toolBar.file.item.ToolBarFileItemMenuImpl;
import utils.ToolBarUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Map;

/**
 * Created by vicboma on 23/08/15.
 */
public class ToolBarFileImpl implements ToolBarFile {

    public static final String OPEN_ROM = "Open ROM";
    public static final String EMULATE = "Emulate";
    public static final String EXIT = "Exit";
    public static final String PAUSE = "Pause";
    public static final String FILE = "File";
    public static final String RESET = "Reset";
    public static final String ACTION_PERFORMED_FILE_TOOL_BAR_ASYNC = "Action Performed fileToolBar Async: ";
    public static final String COMMAND_NOT_FOUND_IN_FILE_TOOL_BAR = "Command not found in File Tool Bar: ";

    private Menu menu;
    private Map<String, ToolBarFileAction> map;
    private List<MenuItem> menuItem;

    @Inject
    public Injector injector;
    @Inject
    public framework.logger.api.Logger logger;
    @Inject
    public FrameworkContext frameworkContext;
    @Inject
    public ToolBarUtils toolBarUtils;
    @Inject
    public ToolBarFileItemAction toolBarFileItemAction;
    @Inject
    public ToolBarFileItemMenu toolBarFileItemMenu;


    public ToolBarFileImpl() {

    }

    @PostConstruct
    public void initialize() {
        logger.info(this);

        this.map = toolBarFileItemAction.load();

        menuItem = toolBarFileItemMenu.load(this);
        menu = toolBarUtils.createMenu(FILE, menuItem);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String command = e.getItem().toString();

        if (commander(command))
            return;

        logError(command);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (commander(command))
            return;

        logError(command);
    }

    private void logError(String command) {
        logger.error(this, COMMAND_NOT_FOUND_IN_FILE_TOOL_BAR + command);
    }

    private boolean commander(String command) {
        final ToolBarFileAction toolBarFileAction = map.get(command);
        if (toolBarFileAction != null) {
            try {
                logger.info(this, ACTION_PERFORMED_FILE_TOOL_BAR_ASYNC +command);
                frameworkContext.executeQueueAsync(() -> {
                    toolBarFileAction.execute(injector);
                });
            } catch (Exception ex) {
                logger.error(this, ex.getMessage(), ex);
            }
            return true;
        }
        return false;
    }

    @Override
    public Menu getMenu() {
        return this.menu;
    }

    @Override
    public MenuItem getFileEmulate() {
        return menu.getItem(ToolBarFileItemMenuImpl.INDEX_EMULATE);
    }

    @Override
    public MenuItem getFileReset() {
        return menu.getItem(ToolBarFileItemMenuImpl.INDEX_RESET);
    }

    @Override
    public MenuItem getFilePause() {
        return menu.getItem(ToolBarFileItemMenuImpl.INDEX_PAUSE);
    }

}
