package emulator.framework.factory;

/**
 * Created by vicboma on 10/09/15.
 */
public interface FactoryCollection<T extends Number> {
    T read(int addres);

    void write(int addres, T data);
}
