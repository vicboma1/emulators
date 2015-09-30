package emulator.toolBar.file.item;

import toolBar.file.ToolBarFile;

import java.awt.*;

/**
 * Created by vicboma on 30/09/15.
 */
public interface ToolBarFileItemMenu {
    java.util.List<MenuItem> load(ToolBarFile toolBarFile);
}
