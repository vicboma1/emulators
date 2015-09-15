package emulator.framework.container;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vicboma on 10/09/15.
 */
public class ClassToMapContainer {

    private Map<ClassToMapKey<?>, Object> map;

    public ClassToMapContainer() {
        map = new HashMap();
    }

    public <T> Object put(ClassToMapKey<T> key, T value ) {
        return map.put( key, value );
    }

    public <T> T get(ClassToMapKey<T> key ) {
        return key._class.cast(map.get(key));
    }

}
