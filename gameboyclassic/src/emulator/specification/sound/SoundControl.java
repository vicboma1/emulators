package emulator.specification.sound;

import emulator.specification.sound.ch1_2.QuadrangularWaveForm;
import emulator.specification.sound.ch3.ArbitraryWaveForm;
import emulator.specification.sound.ch4.WhiteNoise;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicboma on 10/09/14.
 */
public class SoundControl {

    private static final int BUFFER_PLAYBACK = 200;

    private static final int SAMPLE_RATE = 44100;
    private static final int SAMPLE_SIZE_IN_BITS = 8;
    private static final int CHANNELS = 2;
    private static final int FRAME_SIZE = 2;
    private static final float FRAME_RATE = 44100;
    private static final boolean BIG_INDIAN = true;
    public static final int MILLISECONDS = 1000;

    /**
     * 0 = channel1 / Sweep and envelope functions
     * 1 = channel2 / envelope functions
     * 2 = channel3 / generated WaveForm
     * 3 = channel4 / generated noise
     */
    private List<Channel> channel = new ArrayList();
    private boolean enable;
    private SourceDataLine soundLine;

    public SoundControl() {
        initialize();
        channel.add(new QuadrangularWaveForm(SAMPLE_RATE));
        channel.add(new QuadrangularWaveForm(SAMPLE_RATE));
        channel.add(new ArbitraryWaveForm(SAMPLE_RATE));
        channel.add(new WhiteNoise(SAMPLE_RATE));
    }

    public void initialize() {
        try {
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, FRAME_SIZE, FRAME_RATE, BIG_INDIAN);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            if (AudioSystem.isLineSupported(info)) {
                soundLine = (SourceDataLine) AudioSystem.getLine(info);
                final int bufferLength = (SAMPLE_RATE / MILLISECONDS) * BUFFER_PLAYBACK;
                soundLine.open(format, bufferLength);
                soundLine.start();
                enable = true;
            }
        } catch (Exception e) {
            System.out.println("Error: SoundControl");
            e.printStackTrace();
            enable = false;
        } finally {
        }
    }

    public Channel channel(Integer index) {
        return channel.get(index);
    }

    public Boolean enable() {
        return enable;
    }

    public void play() {
        if (enable) {
            byte[] buffer = new byte[SAMPLE_RATE];
            channel.stream()
                    .sequential()
                    .filter(ch -> ch.channelEnable())
                    .forEach(x -> {
                        x.play(buffer, SAMPLE_RATE / 2, 0);
                    });

            soundLine.write(buffer, 0, SAMPLE_RATE);
        }
    }

}


