package emulator.framework.logger.api;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 11/03/14
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public interface Logger {
   <E> void info(E clazz, String log);
   <E> void info(E clazz);
   <E> void info(E clazz, String log, Throwable throwable);
   <E> void debug(E clazz, String log);
   <E> void debug(E clazz, String log, Throwable throwable);
   <E> void error(E clazz, String log);
   <E> void error(E clazz, String log, Throwable throwable);
}
