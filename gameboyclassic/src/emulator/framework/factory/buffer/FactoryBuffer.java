package emulator.framework.factory.buffer;

import java.nio.ByteBuffer;

/**
 * Created by vicboma on 15/09/15.
 */
public interface FactoryBuffer {
    Byte read(int addres);
    ByteBuffer write(int addres, byte data);
}
