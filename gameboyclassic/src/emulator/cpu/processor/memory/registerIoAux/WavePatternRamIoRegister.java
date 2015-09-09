package emulator.cpu.processor.memory.registerIoAux;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 08/09/15.
 */
public class WavePatternRamIoRegister {

    Map<Integer, Byte> map;

    public WavePatternRamIoRegister() {
        map = new Hashtable();
    }


    public Byte get(Integer index){
        final Byte value = this.map.get(index);
        return value;
    }

    public Byte put(Integer key, byte value){
        return this.map.put(key,value);
    }
}
