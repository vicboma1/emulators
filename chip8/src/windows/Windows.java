package windows;

import emulator.specification.SpecificationChip8Impl;
import emulator.specification.input.KeyBoard;
import emulator.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;

/**
 * Created by vicboma on 09/07/14.
 */
public class Windows {

    public static final String CHIP8_JAVA_EMULATOR = "Chip8 Java Emulator";
    private JFrame jFrame;

    Windows(final JFrame frame, final WindowListener windowListener, final SpecificationChip8Impl specificationChip8Impl, final KeyBoard keyBoard) {
        this.jFrame = frame;
        configureGUI(frame, specificationChip8Impl);

        frame.setTitle(CHIP8_JAVA_EMULATOR);
        frame.setLocation(Utils.DisplayModeCenterX(), Utils.DisplayModeCenterY());
        frame.setResizable(Utils.FALSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.addWindowListener(windowListener);
        frame.addKeyListener(keyBoard.listener());

        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        specificationChip8Impl.initialize();
        panel.add(specificationChip8Impl.video().component());

        frame.setContentPane(panel);
        frame.repaint();
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
    }

    public static Windows create(final SpecificationChip8Impl specificationChip8Impl, final KeyBoard keyBoard) {
        final JFrame frame = new JFrame();
        final WindowListener windowListener = WindowsListenerImpl.create();
        return new Windows(frame, windowListener, specificationChip8Impl, keyBoard);
    }

    private void configureGUI(JFrame frame, SpecificationChip8Impl specificationChip8Impl) {
        final JCheckBoxMenuItem soundOff = new JCheckBoxMenuItem("Sound Off");

        final JMenuItem loadRom = new JMenuItem("Load Rom");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenuItem about = new JMenuItem("About");

        final JMenu fileMenu = new JMenu("File");
        fileMenu.add(loadRom);
        fileMenu.add(exit);

        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.add(soundOff);

        final JMenu helpMenu = new JMenu("Help");
        helpMenu.add(about);

        final JMenuBar menu = new JMenuBar();
        menu.add(fileMenu);
        menu.add(optionsMenu);
        menu.add(helpMenu);

        frame.setJMenuBar(menu);

        final ActionListener actionListener = WindowsActionListenerImpl.create(specificationChip8Impl, frame, new JMenuItem[]{loadRom, about, exit});
        final ItemListener itemListener = WindowsItemListenerImpl.create(specificationChip8Impl, soundOff);
    }

    public JFrame component() {
        return this.jFrame;
    }
}
