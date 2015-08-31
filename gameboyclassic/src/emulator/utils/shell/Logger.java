package emulator.utils.shell;

import utils.shell.pool.LoggerPoolAsyncImpl;

import java.awt.*;

/**
 * Created by vicboma on 02/01/15.
 */
public class Logger {

    private static Console console;
    public static final String LUCIDA_CONSOLE = "Lucida Console";
    public static final String QUEUE_TASK = " Queue task : ";

    public static void Console(Dialog dialog, LoggerPoolAsyncImpl loggerPoolAsync){
        console = new Console(dialog,loggerPoolAsync);
    }

    // basic println
    static public void Debug(String s) {
        console.println("Debug: " + s);
    }

    static public void DebugValidate(String s) {
        console.printValidateln("Debug: " + s);
    }

    static public void DebugToolBar(String s) {
        console.printToolBarln("Debug: " + s);
    }


    //uncontrollable
    static public void Error(String s) {
        console.printErrorln("Error: " + s);
    }

    // error controllable
    static public void Warning(String s) {
        console.printWarningln("Warning: " + s);
    }

    static public void WarningInLine(String s) {
        console.printWarning(s);
    }


    static public void DebugInLine(String s) {
        console.println(s);
    }

}
