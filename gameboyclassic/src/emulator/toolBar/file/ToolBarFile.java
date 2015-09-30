package emulator.toolBar.file;

import toolBar.ToolBarMenu;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Created by vicboma on 23/08/15.
 */
public interface ToolBarFile extends ActionListener, ItemListener, ToolBarMenu {
    MenuItem getFileEmulate();
    MenuItem getFileReset();
    MenuItem getFilePause();
}
