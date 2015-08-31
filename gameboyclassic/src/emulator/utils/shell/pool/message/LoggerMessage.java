package emulator.utils.shell.pool.message;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vicboma on 31/08/15.
 */
public interface LoggerMessage {
    Label getLabel();
    JTextPane getPrintln();
    String getMsn();
    Color getColor();
}
