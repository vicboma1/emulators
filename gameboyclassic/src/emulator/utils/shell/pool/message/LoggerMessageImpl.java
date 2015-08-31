package emulator.utils.shell.pool.message;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vicboma on 31/08/15.
 */
public class LoggerMessageImpl implements LoggerMessage {
    private JTextPane println;
    private String msn;
    private Color color;
    private Label l;

    public LoggerMessageImpl(JTextPane println, String msn, Color color, Label l) {
        this.println = println;
        this.msn = msn;
        this.color = color;
        this.l = l;

    }

    @Override
    public Label getLabel() {
        return l;
    }

    @Override
    public JTextPane getPrintln() {
        return println;
    }

    @Override
    public String getMsn() {
        return msn;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
