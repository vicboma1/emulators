package emulator.utils.shell.pool.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by vicboma on 31/08/15.
 * Pattern Thread Factory
 */
public class WorkerThreadFactory implements ThreadFactory {

    public static final String SEPARATOR = " - ";
    private AtomicInteger counter;
    private String prefix = "";

    public static WorkerThreadFactory Create(String prefix){
        return new WorkerThreadFactory(prefix);
    }

    WorkerThreadFactory(String prefix) {
        this.prefix = prefix;
        this.counter = new AtomicInteger(0);
    }

    public Thread newThread(Runnable runnable) {
        final int counter = this.counter.incrementAndGet();
        return new Thread(runnable, prefix + SEPARATOR + counter);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder()
                .append(prefix)
                .append(SEPARATOR)
                .append(counter.intValue());

        return sb.toString();
    }

}