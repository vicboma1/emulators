package emulator.framework.injector.api.type;

/**
 * Created by vicboma on 28/09/15.
 */
public interface InjectorFactory {
    Object createInstance(Class _class) throws Exception;
}
