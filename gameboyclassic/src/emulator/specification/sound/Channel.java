package emulator.specification.sound;

/**
 * Created by vicboma on 10/09/14.
 */
public interface Channel {

    void setSampleRate(Integer sampleRate);

    Boolean channelEnable();

    void channelEnable(Boolean active);

    void play(byte[] b, Integer length, Integer offset);

    void setLength(int gbLength);

    void setEnvelope(Integer initialValue, Integer numSteps, Boolean increase);

    void setChannel(Integer channel);

    abstract void setSweep(Integer time, Integer num, Boolean decrease);

    abstract void setDutyCycle(Integer duty);

    abstract void setFrequency(Integer gbFrequency);

    abstract void setVolume(Integer volume);

    abstract void setParameters(float dividingRatio, Boolean polynomialSteps, Integer shiftClockFreq);

    abstract void setSamplePair(int address, int value);
}
