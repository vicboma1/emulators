package emulator.main.loop;

import framework.injector.api.Injector;

import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 29/09/15.
 */
public interface ExecuteOption {
    void execute(Injector injector) throws ExecutionException, InterruptedException;
}