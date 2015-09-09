package emulator.cpu.processor.memory.registerIoAux;

/**
 * Created by vicboma on 08/09/15.
 */
public class WhiteNoiseIoRegister {
    private byte envelopeRegister;
    private byte setLengthData;
    private byte polynomialCycleRegister;
    private byte counter;

    public WhiteNoiseIoRegister() {
    }

    public byte envelopeRegister() {
        return this.envelopeRegister;
    }

    public void envelopeRegister(byte envelopeRegister) {
        this.envelopeRegister = envelopeRegister;
    }

    public byte setLengthData() {
        return this.setLengthData;
    }

    public void setLengthData(byte setLengthData) {
        this.setLengthData = setLengthData;
    }

    public byte polynomialCycleRegister() {
        return this.polynomialCycleRegister;
    }

    public void polynomialCycleRegister(byte polynomialCycleRegister) {
        this.polynomialCycleRegister = polynomialCycleRegister;
    }

    public byte counter() {
        return this.counter;
    }

    public void counter(byte counter) {
        this.counter = counter;
    }
}
