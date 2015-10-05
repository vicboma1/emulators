package emulator.toolBar.others.item;

import toolBar.others.ToolBarOthers;

import java.awt.*;

/**
 * Created by vicboma on 05/10/15.
 */
public interface ToolBarOthersItemMenu {
    java.util.List<MenuItem> loadMenu(ToolBarOthers toolBarOthers);
    java.util.List<CheckboxMenuItem> loadDisassembler(ToolBarOthers toolBarOthers);
}
