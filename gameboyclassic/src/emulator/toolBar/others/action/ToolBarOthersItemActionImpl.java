package emulator.toolBar.others.action;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.logger.api.Logger;
import framework.pattern.action.ActionInjector;
import utils.toolBar.ToolBarUtils;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 30/09/15.
 */
public class ToolBarOthersItemActionImpl implements ToolBarOthersItemAction {

    public static final String CONSOLE = "Console";

    public static final String OTHERS = "Others";

    public static final String DISASSEMBLER = "Disassembler";
    public static final String HEXADECIMAL = "Hexadecimal";
    public static final String DECIMAL = "Decimal";

    public static boolean isHexadecimal;
    public static boolean isDecimal;

    @Inject
    public Logger logger;
    @Inject
    public ToolBarUtils toolBarUtils;

    public ToolBarOthersItemActionImpl() {
        isHexadecimal = false;
        isDecimal = false;
    }

    @PostConstruct
    public void initialize() {
        logger.info(this);
    }

    @Override
    public Map<String, ActionInjector> load() {
        return new Hashtable() {
            {
                put(CONSOLE, (ActionInjector) (injector) -> {
                    toolBarUtils.addOptionAsync(injector,CONSOLE);
                });
                put(HEXADECIMAL, (ActionInjector) (injector) -> {
                    toolBarUtils.addOptionAsync(injector,HEXADECIMAL);
                });
                put(DECIMAL, (ActionInjector) (injector) -> {
                    toolBarUtils.addOptionAsync(injector,DECIMAL);
                });
            }

        };
    }
}
