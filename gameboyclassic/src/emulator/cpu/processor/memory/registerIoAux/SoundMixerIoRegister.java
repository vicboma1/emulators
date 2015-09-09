package emulator.cpu.processor.memory.registerIoAux;

/**
 * Created by vicboma on 08/09/15.
 */
public class SoundMixerIoRegister {
    private WavePatternRamIoRegister wavePatternRamIoRegister;
    private byte channelControl;
    private byte channelOutput;
    private byte soundOnOff;

    public SoundMixerIoRegister() {
        wavePatternRamIoRegister = new WavePatternRamIoRegister();
    }

    public byte channelControl() {
        return this.channelControl;
    }
    public void channelControl(byte channelControl) {
        this.channelControl = channelControl;
    }

    public byte channelOutput() {
        return this.channelOutput;
    }
    public void channelOutput(byte register) {
        this.channelOutput = register;
    }

    public byte soundOnOff() {
        return this.soundOnOff;
    }
    public void soundOnOff(byte soundOnOff) {
        this.soundOnOff = soundOnOff;
    }

    public WavePatternRamIoRegister wavePatternRamIoRegister(){ return  this.wavePatternRamIoRegister; }

}
