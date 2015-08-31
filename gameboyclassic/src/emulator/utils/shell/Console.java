package emulator.utils.shell;

import emulator.utils.shell.pool.LoggerPoolMessage;
import utils.shell.pool.message.LoggerMessage;
import utils.shell.pool.message.LoggerMessageImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by vicboma on 19/08/15.
 */
class Console {


    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color BLUE = new Color(49, 143, 188);
    public static final Color GREEN = new Color(71, 159, 37);
    public static final Color RED = new Color(192, 28, 15);
    public static final Color ORANGE = new Color(201, 89, 0);
    private static JTextPane println;

    public static final String WARNING = "Warning";
    public static final String ERROR = "Error";
    public static final int WIDTH = 620;
    public static final int HEIGHT = 640;
    public static final String CENTER = "Center";
    public static final String NORTH = "North";
    public static final String CLOSE = "Close";
    public static final String CLOSE_DIALOG = "Close dialog";
    public static final String SOUTH = "South";

    private Dialog dialog;
    private static LoggerPoolMessage loggerPoolAsyncImpl;
    private static Label l;

    public Console(Dialog dialog, LoggerPoolMessage loggerPoolAsyncImpl) {
        this.loggerPoolAsyncImpl = loggerPoolAsyncImpl;
        this.dialog = dialog;
        dialog.setVisible(false);
        println = new JTextPane();
        println.setBorder(getBorderEmpty());
        addLabel();
        dialog.add(getScrollPanel());
        dialog.setSize(WIDTH, HEIGHT);
        dialog.setVisible(true);
    }

    private JScrollPane getScrollPanel() {
        return new JScrollPane(println);
    }

    private EmptyBorder getBorderEmpty() {
        return new EmptyBorder(new Insets(10, 10, 10, 10));
    }

    private void addLabel() {
        l = new Label();
        dialog.add(l, SOUTH);
    }

    public static void printValidateln(String str) {
        appendToPane(str + "\n", GREEN);
    }

    public static void printToolBarln(String str) {
        appendToPane(str + "\n", BLUE);
    }

    public static void println(String str) {
        appendToPane(str + "\n", BLACK);
    }

    public static void printErrorln(String str) {
        appendToPane(str + "\n", RED);
    }

    public static void printWarningln(String str) {
        appendToPane(str + "\n", ORANGE);
    }

    public static void printWarning(String str) {
        appendToPane(str, ORANGE);
    }

    private static void appendToPane(String msg, Color c) {
        l.setText(Logger.QUEUE_TASK + loggerPoolAsyncImpl.size());
        final LoggerMessage loggerMessage = new LoggerMessageImpl(println, msg, c, l);
        loggerPoolAsyncImpl.add(loggerMessage);
    }


}
