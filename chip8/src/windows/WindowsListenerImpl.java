package windows;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by vicboma on 09/07/14.
 */
public class WindowsListenerImpl implements WindowListener {

    WindowsListenerImpl() {
    }

    public static WindowListener create() {
        return new WindowsListenerImpl();
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        e.getWindow().dispose();
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
