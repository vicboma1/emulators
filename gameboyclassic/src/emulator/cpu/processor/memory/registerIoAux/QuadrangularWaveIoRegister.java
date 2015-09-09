package emulator.cpu.processor.memory.registerIoAux;

/**
 * Created by vicboma on 08/09/15.
 */
public class QuadrangularWaveIoRegister {
    private byte sweepRegister;
    private byte lengthWaveRegister;
    private byte envelopeRegister;
    private byte frequencyRegisterLO;
    private byte frequencyRegisterHI;

    public QuadrangularWaveIoRegister() {
    }

    public byte sweepRegister() {
        return this.sweepRegister;
    }

    public void sweepRegister(byte sweepRegister) {
        this.sweepRegister = sweepRegister;
    }

    public byte lengthWaveRegister() {
        return this.lengthWaveRegister;
    }

    public void lengthWaveRegister(byte lengthWaveRegister) {
        this.lengthWaveRegister = lengthWaveRegister;
    }

    public byte envelopeRegister() {
        return this.envelopeRegister;
    }

    public void envelopeRegister(byte envelopeRegister) {
        this.envelopeRegister = envelopeRegister;
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
