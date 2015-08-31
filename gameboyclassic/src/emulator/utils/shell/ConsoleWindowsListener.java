package emulator.utils.shell;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by vicboma on 31/08/15.
 */
public class ConsoleWindowsListener implements WindowListener {

    private Dialog dialog;
    public static ConsoleWindowsListener Create(Dialog dialog){
        return new ConsoleWindowsListener(dialog);
    }

    ConsoleWindowsListener(Dialog dialog) {
        this.dialog = dialog;
        this.dialog.addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
         dialog.setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
