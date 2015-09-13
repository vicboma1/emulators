package emulator.framework.factory;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.mock;

public class FactoryCollectionImplTest extends TestCase {

    private FactoryCollection<Byte> factoryCollection;

    public void setUp() throws Exception {
        super.setUp();

        List<Byte> list = mock(List.class);
        factoryCollection = new FactoryCollectionImpl<Byte>(list);
    }

    public void tearDown() throws Exception {
        factoryCollection = null;
    }

    public void testCreate() throws Exception {
        List<Byte> _list = mock(List.class);
        final FactoryCollection<Byte> _factoryCollection = FactoryCollectionImpl.Create(_list);
        assertNotSame("Not same testCreate",_factoryCollection, factoryCollection);
    }

    public void testInitialize() throws Exception {
        final List<ArrayList> initialize = FactoryCollectionImpl.initialize(100, new ArrayList());
        final List<ArrayList> _initialize = FactoryCollectionImpl.initialize(100, new ArrayList());
        assertNotSame("Not same testInitialize" ,_initialize , initialize);
    }

    public void testRead() throws Exception {
        final int size = 100;
        final int defautl = -1;
        final List<Byte> initialize = FactoryCollectionImpl.initialize(size, (byte) defautl);
        final FactoryCollection<Byte> factoryCollect = FactoryCollectionImpl.Create(initialize);
        IntStream.range(0, size).boxed().forEach(
                x ->
                {
                    final byte read = factoryCollect.read(x);
                    assertTrue("Not same testRead", read == defautl);
                });
    }

    public void testWrite() throws Exception {

        final int address = 100;
        final byte data = -1;
        final Byte write = factoryCollection.write(address, data);
        assertNull("Not same object",write);
    }
}