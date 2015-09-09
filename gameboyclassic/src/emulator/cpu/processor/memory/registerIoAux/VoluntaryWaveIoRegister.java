package emulator.cpu.processor.memory.registerIoAux;

/**
 * Created by vicboma on 08/09/15.
 */
public class VoluntaryWaveIoRegister {
    private byte volumeRegister;
    private byte setLengthData;
    private byte outputLevel;
    private byte frequencyRegisterLO;
    private byte frequencyRegisterHI;

    public VoluntaryWaveIoRegister() {
    }

    public byte volumeRegister() {
        return this.volumeRegister;
    }

    public void volumeRegister(byte volumeRegister) {
        this.volumeRegister = volumeRegister;
    }

    public byte setLengthData() {
        return this.setLengthData;
    }

    public void setLengthData(byte setLengthData) {
        this.setLengthData = setLengthData;
    }

    public byte outputLevel() {
        return this.outputLevel;
    }

    public void outputLevel(byte outputLevel) {
        this.outputLevel = outputLevel;
    }

    public byte frequencyRegisterLO() {
        return this.frequencyRegisterLO;
    }

    public void frequencyRegisterLO(byte frequencyRegisterLO) {
        this.frequencyRegisterLO = frequencyRegisterLO;
    }

    public byte frequencyRegisterHI() {
        return this.frequencyRegisterHI;
    }

    public void frequencyRegisterHI(byte frequencyRegisterHI) {
        this.frequencyRegisterHI = frequencyRegisterHI;
    }




}
