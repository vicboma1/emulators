package emulator.framework.logger.src;

import framework.injector.api.type.Injector;
import framework.logger.api.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 11/03/14
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class LoggerImpl implements Logger
{
    public static Logger create()
    {
        return new LoggerImpl();
    }

    LoggerImpl() { info(this);}

    public <E> void info(E clazz, String log)
    {
        org.apache.log4j.Logger.getLogger(clazz.getClass()).info(log);
    }

    public <E> void info(E clazz)
    {
        final Class<?> aClass = clazz.getClass();
        org.apache.log4j.Logger.getLogger(aClass).info(Injector.POST_CONSTRUCTOR+" "+aClass.getName());
    }

    public <E> void info(E clazz, String log, Throwable throwable)
    {
        org.apache.log4j.Logger.getLogger(clazz.getClass()).info(log, throwable);
    }

    public <E> void debug(E clazz, String log)
    {
        org.apache.log4j.Logger.getLogger(clazz.getClass()).debug(log);
    }

    public <E> void debug(E clazz, String log, Throwable throwable)
    {
        org.apache.log4j.Logger.getLogger(clazz.getClass()).debug(log, throwable);
    }

    public <E> void error(E clazz, String log)
    {
        org.apache.log4j.Logger.getLogger(clazz.getClass()).error(log);
    }

    public <E> void error(E clazz, String log, Throwable throwable)
    {
        org.apache.log4j.Logger.getLogger(clazz.getClass()).error(log, throwable);
    }



}
