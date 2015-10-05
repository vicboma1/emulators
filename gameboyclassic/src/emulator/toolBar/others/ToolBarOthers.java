package emulator.toolBar.others;

import toolBar.ToolBarMenu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Created by vicboma on 31/08/15.
 */
public interface ToolBarOthers extends ItemListener, ToolBarMenu, ActionListener {
    CheckboxMenuItem getOthersConsole();
    CheckboxMenuItem getItemDisassembler(int index);
}
