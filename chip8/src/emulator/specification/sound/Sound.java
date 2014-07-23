package emulator.specification.sound;

import emulator.utils.Utils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Sound {
    public static final int OFF = Utils.ZERO;
    public static final int INIT = Utils.ZERO;
    private static final float SAMPLE_RATE = 48000;
    private static final int SAMPLE_SIZE_IN_BITS = 8;
    private static final int CHANNELS = 2;
    private static final int FRAME_SIZE = 2;
    private static final float FRAME_RATE = 48000;
    private static final boolean BIG_INDIAN = true;
    private static final byte BIT_SOUND = 0x20;  // -32
    private SourceDataLine soundLine;
    private boolean isEnable;
    private byte[] buffer;


    public Sound() {
        initialize();
    }

    private void initialize() {
        try {
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, SAMPLE_RATE, SAMPLE_SIZE_IN_BITS, CHANNELS, FRAME_SIZE, FRAME_RATE, BIG_INDIAN);
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            if (AudioSystem.isLineSupported(info)) {
                soundLine = (SourceDataLine) AudioSystem.getLine(info);
                soundLine.open(format);
                soundLine.start();
                isEnable = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            isEnable = false;
        } finally {
            buffer = new byte[CHANNELS];
        }
    }

    public void play() {
        if (isEnable) {
            for (int i = INIT; i < buffer.length; i++)
                buffer[i] = BIT_SOUND;

            soundLine.write(buffer, OFF, CHANNELS);
        }
    }
}
