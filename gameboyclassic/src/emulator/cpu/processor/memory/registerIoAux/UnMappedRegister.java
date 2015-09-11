package emulator.cpu.processor.memory.registerIoAux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by vicboma on 11/09/15.
 */
public class UnMappedRegister {
    private static final int SIZE = 0x100;
    private List<Byte> list;

    public static UnMappedRegister Create(){
        List<Byte> initialize = initialize();
        return new UnMappedRegister(initialize);
    }

    public static List<Byte> initialize(){
        final List<Byte> list = new ArrayList();
        IntStream.range(0,SIZE).boxed().forEach(x -> list.add((byte) 0));
        return list;
    }


    public UnMappedRegister(List<Byte> list){
        this.list = list;
    }


    public Byte read(int address) {
        final byte read = list.get(address);
        return read;
    }

    public void write(int address, byte data) {
        final byte set = list.set(address, data);
    }


}
