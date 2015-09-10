package emulator.framework.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by vicboma on 10/09/15.
 */
public class FactoryCollectionImpl<T extends Number> implements FactoryCollection<T> {
    private List<T> data;

    public static <H extends Number> FactoryCollection<H> Create(List<H> list){
        return (FactoryCollection) new FactoryCollectionImpl<H>(list);
    }

    public static <T> List<T> initialize(final int size, final T defautl){
        final List<T> list = new ArrayList(size);
        IntStream.range(0,size).boxed().forEach(x -> list.add(x, defautl));
        return list;
    }

    public FactoryCollectionImpl(List<T> _data) {
        this.data = _data;
    }

    @Override
    public T read(int addres){
        return this.data.get(addres);
    }

    @Override
    public void write(int addres, T data) {
        this.data.set(addres,data);
    }

}
