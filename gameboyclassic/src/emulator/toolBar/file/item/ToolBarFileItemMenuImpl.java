package emulator.toolBar.file.item;

import framework.injector.api.annotation.Inject;
import framework.injector.api.annotation.method.PostConstruct;
import framework.logger.api.Logger;
import toolBar.file.ToolBarFile;
import toolBar.file.ToolBarFileImpl;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by vicboma on 30/09/15.
 */
public class ToolBarFileItemMenuImpl implements ToolBarFileItemMenu {

    @Inject
    public Logger logger;

    public ToolBarFileItemMenuImpl() {
    }

    @PostConstruct
    public void initialize(){
        logger.info(this);
    }

    @Override
    public java.util.List<MenuItem> load(ToolBarFile toolBarFile) {
        return new ArrayList() {
            {
                add(0, new MenuItem() {
                    {
                        setLabel(ToolBarFileImpl.OPEN_ROM);
                        setActionCommand(ToolBarFileImpl.OPEN_ROM);
                        addActionListener(toolBarFile);
                    }
                });
                add(1, null); //Seperator
                add(2, new MenuItem() {
                    {
                        setLabel(ToolBarFileImpl.EMULATE);
                        setActionCommand(ToolBarFileImpl.EMULATE);
                        addActionListener(toolBarFile);
                        setEnabled(false);
                    }
                });
                add(3, new CheckboxMenuItem() {
                    {
                        setLabel(ToolBarFileImpl.PAUSE);
                        setActionCommand(ToolBarFileImpl.PAUSE);
                        addItemListener(toolBarFile);
                        setEnabled(false);
                    }
                });
                add(4, new MenuItem() {
                    {
                        setLabel(ToolBarFileImpl.RESET);
                        setActionCommand(ToolBarFileImpl.RESET);
                        addActionListener(toolBarFile);
                        setEnabled(false);
                    }
                });
                add(5, null); //Seperator
                add(6, new MenuItem() {
                    {
                        setLabel(ToolBarFileImpl.EXIT);
                        setActionCommand(ToolBarFileImpl.EXIT);
                        addActionListener(toolBarFile);
                    }
                });
            }
        };
    }
}
