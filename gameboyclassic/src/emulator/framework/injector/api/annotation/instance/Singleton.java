package emulator.framework.injector.api.annotation.instance;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 14/02/14
 * Time: 18:38
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Singleton {
    String name();
}
