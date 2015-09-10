package emulator.cpu.processor.mainRam;

import framework.factory.FactoryCollectionImpl;

import java.util.List;

/**
 * Created by vicboma on 09/09/15.
 * Represented a Main Ram Processor
 */
public class MainRam<T extends Number> extends FactoryCollectionImpl<T> {

    // 32Kb for GBC ( 32768 )
    public static final int mainRamGBC = 0x32768;
    // 8Kb main system RAM
    public static final int mainRamDMG = 0x8000;

    public static <H extends Number> MainRam<H> Create(final int size, final H _default) {
        List<H> list = FactoryCollectionImpl.initialize(size, _default);
        return new MainRam(list);
    }

    public MainRam(final List<T> mainRam) {
        super(mainRam);
    }
}
