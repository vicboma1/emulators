package emulator.framework.container;

/**
 * Created by vicboma on 10/09/15.
 */
public class ClassToMapKey<T> {

    final String id;
    final Class<? extends T> _class;

    public static <T> ClassToMapKey Create(String id, Class<? extends T> _class){
        return new ClassToMapKey(id, _class);
    }

    ClassToMapKey(String id, Class<? extends T> _class) {
        this.id = id;
        this._class = _class;
    }

    public String id() {
        return id;
    }

    public Class<? extends T> _class() {
        return _class;
    }
}
