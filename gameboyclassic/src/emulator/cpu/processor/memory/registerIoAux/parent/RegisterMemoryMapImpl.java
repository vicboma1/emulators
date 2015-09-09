package emulator.cpu.processor.memory.registerIoAux.parent;

/**
 * Created by vicboma on 08/09/15.
 */
public class RegisterMemoryMapImpl implements RegisterMemoryMap {
    private byte register;

    public RegisterMemoryMapImpl() {
        this.register = 0;
    }

    @Override
    public byte register() {
        return register;
    }

    @Override
    public void register(byte register) {
        this.register = register;
    }
}
