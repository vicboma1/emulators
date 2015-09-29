package emulator.framework.injector.api.type;

import framework.injector.src.state.StoreLocation;

/**
 * Created with IntelliJ IDEA.
 * User: vicboma
 * Date: 14/05/14
 * Time: 19:43
 */
public interface Instance {
        Object getInstance(InjectorFactory factory) throws Exception;
        StoreLocation getStoreLocation();
}
