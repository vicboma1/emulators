package emulator.cpu.processor.spriteAttribMemory;

import framework.factory.FactoryCollectionImpl;

import java.util.List;

/**
 * Created by vicboma on 09/09/15.
 * Represented a Sprite Attribute Memory
 */
public class OAM<T extends Number> extends FactoryCollectionImpl<T> {

    public static final int size = 0xA0;  // $FE00-FE9F   1registro lo recorre 4 veces, 40 * 4 = 160 = 0xA0

    public static <H extends Number> OAM<H> Create(final int size, H _default){
        List<H> list = FactoryCollectionImpl.initialize(size, _default);
        return new OAM(list);
    }

    public OAM(final List<T> mainRam) {
        super(mainRam);
    }
}
