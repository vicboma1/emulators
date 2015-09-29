package emulator.framework.factory.buffer;

import framework.factory.collection.FactoryCollection;

import java.nio.ByteBuffer;

/**
 * Created by vicboma on 10/07/15.
 */
public class FactoryBufferImpl implements FactoryBuffer {
    private ByteBuffer data;

    public static FactoryCollection Create(ByteBuffer byteBuffer){
        return (FactoryCollection) new FactoryBufferImpl(byteBuffer);
    }

    public static ByteBuffer initialize(final int size){
        final ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        return byteBuffer;
    }

    public FactoryBufferImpl(ByteBuffer _data) {
        this.data = _data;
    }

    @Override
    public Byte read(int addres){
        return this.data.get(addres);
    }

    @Override
    public ByteBuffer write(int addres, byte data) {
        final ByteBuffer put = this.data.put(addres, data);
        return put;
    }

}
