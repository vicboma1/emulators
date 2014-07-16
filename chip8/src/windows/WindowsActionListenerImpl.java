package windows;

import emulator.specification.SpecificationChip8Impl;
import emulator.utils.Loader;
import emulator.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by vicboma on 09/07/14.
 */
public class WindowsActionListenerImpl implements ActionListener {

    public static final String REQUEST_FOCUS = "requestFocus";
    public static final String ABOUT = "About";
    public static final String LOAD_ROM = "Load Rom";
    public static final String EXIT = "Exit";
    public static final String OK = " OK!";
    public static final String KO = " KO!";
    private static final String CHIP8_JAVA_EMULATOR_COPYRIGHT_C_2014_VICTOR_BOLINCHES = "Chip8 Java Emulator \nCopyright (c) 2014 Victor Bolinches";
    private static final String SELECT_CHIP8_SUPER_CHIP_ROM = "Select Chip8/Super-Chip Rom";
    private Map<String, ActionCommand> mapActions;

    WindowsActionListenerImpl(JMenuItem[] jMenuItems, Map<String, ActionCommand> mapActions) {
        addListeners(jMenuItems);
        this.mapActions = mapActions;
    }

    public static ActionListener create(final SpecificationChip8Impl specificationChip8Impl, final JFrame frame, final JMenuItem[] menuItems) {
        return new WindowsActionListenerImpl(menuItems,
                new Hashtable<String, ActionCommand>() {
                    {
                        put(LOAD_ROM, () -> {
                            specificationChip8Impl.stopAsyncTask();
                            FileDialog loadFile = new FileDialog(frame, SELECT_CHIP8_SUPER_CHIP_ROM, FileDialog.LOAD);
                            loadFile.setDirectory("./roms/c");
                            loadFile.setFilenameFilter((dir, name) -> (name.contains(".rom")));
                            loadFile.setVisible(true);

                            final boolean isLoaded = Loader.rom(loadFile.getDirectory() + loadFile.getFile(), specificationChip8Impl.memory());
                            if (isLoaded) {
                                System.out.println(LOAD_ROM + OK);
                                specificationChip8Impl.dispose();
                                specificationChip8Impl.loadContent();
                                specificationChip8Impl.runAsyncTask();
                            } else
                                System.out.println(LOAD_ROM + KO);

                            this.get(REQUEST_FOCUS).execute();
                        });
                        put(ABOUT, () -> {
                            JOptionPane.showMessageDialog(frame, CHIP8_JAVA_EMULATOR_COPYRIGHT_C_2014_VICTOR_BOLINCHES);
                            this.get(REQUEST_FOCUS).execute();
                        });
                        put(EXIT, () -> {
                            WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
                            System.gc();
                            System.exit(Utils.ZERO);
                        });
                        put(REQUEST_FOCUS, () -> {
                            frame.requestFocus();
                        });
                    }
                });
    }

    private void addListeners(JMenuItem[] jMenuItems) {
        IntStream stream = IntStream.range(0, jMenuItems.length);
        stream.sequential().forEach(x -> jMenuItems[x].addActionListener(this));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final String itemSelected = event.getActionCommand();
        if (this.mapActions.containsKey(itemSelected))
            this.mapActions.get(itemSelected).execute();
    }
}
