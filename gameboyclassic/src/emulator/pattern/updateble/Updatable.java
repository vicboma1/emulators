package emulator.pattern.updateble;

import java.awt.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by vicboma on 06/09/15.
 */
public interface Updatable {
    boolean update(Graphics g, int startX, int startY) throws ExecutionException, InterruptedException;
}
