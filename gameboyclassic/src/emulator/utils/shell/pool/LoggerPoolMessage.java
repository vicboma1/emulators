package emulator.utils.shell.pool;

import utils.shell.pool.message.LoggerMessage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vicboma on 31/08/15.
 */
public interface LoggerPoolMessage {
    void runAsync(JTextPane println, String msn, Color color, Label label);
    LoggerPoolMessage add(LoggerMessage loggerMessage);
    int size();
}
