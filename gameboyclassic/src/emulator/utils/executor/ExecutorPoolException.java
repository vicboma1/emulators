package emulator.utils.executor;

/**
 * Created by vicboma on 02/09/15.
 */
public class ExecutorPoolException extends Exception {
    public ExecutorPoolException() { super(); }
    public ExecutorPoolException(String message) { super(message); }
    public ExecutorPoolException(String message, Throwable cause) { super(message, cause); }
    public ExecutorPoolException(Throwable cause) { super(cause); }
}