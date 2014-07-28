package emulator.specification;

import core.AsyncTask;
import core.Processor;
import emulator.Emulator;
import emulator.instructions.Instructions;
import emulator.specification.font.Font;
import emulator.specification.graphics.BufferPoolImage;
import emulator.specification.graphics.Display;
import emulator.specification.graphics.Screen;
import emulator.specification.graphics.Video;
import emulator.specification.input.KeyBoard;
import emulator.specification.memory.Memory;
import emulator.specification.register.Register;
import emulator.specification.sound.Sound;
import emulator.specification.timer.Timer;
import emulator.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;


public class SpecificationChip8Impl extends Emulator implements SpecificationsChip8 {

    private static final int SCALE_FACTOR = 4;

    private Memory memory;
    private Register register;
    private Sound sound;
    private Video video;
    private Timer timer;
    private Font font;
    private KeyBoard keyBoard;
    private AsyncTask<Processor> asyncTask;

    public SpecificationChip8Impl(KeyBoard keyboard) {
        super();
        this.keyBoard = keyboard;
    }

    @Override
    public void initialize() {
        this.fontConfiguration();
        this.registerConfiguration();
        this.memoryConfiguration();
        this.timerConfiguration();
        this.videoConfiguration();
        this.soundConfiguration();
    }

    private void fontConfiguration() {
        this.font = new Font();
    }

    private void registerConfiguration() {
        register = new Register();
    }

    private void memoryConfiguration() {
        memory = new Memory();
    }

    private void timerConfiguration() {
        timer = new Timer();
    }

    private void soundConfiguration() {
        sound = new Sound();
    }

    private void videoConfiguration() {
        final Screen screen = new Screen();
        final Display display = new Display(Video.WIDTH, Video.HEIGHT);
        final BufferPoolImage bufferPoolImage = new BufferPoolImage(screen);
        final JComponent component = new JComponent() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(Display.WIDTH, Display.HEIGHT);
            }
        };

        video = new Video(display, screen, bufferPoolImage, component);
        video.scale(SCALE_FACTOR);
    }

    @Override
    public void dispose() {
        this.register.dispose();
        this.keyBoard.dispose();
        this.timer.dispose();
    }

    @Override
    public void loadContent() {
        this.memory.loadFont(font);
        //this.memory.loadSuperFont(font);
        clearScreen();
    }

    private void clearScreen() {
        IntStream stream = IntStream.range(Utils.ZERO, Video.WIDTH * Video.HEIGHT);
        stream.sequential().forEach(x -> {
            video.display().matrix(x, Display.BACKGROUND);
        });
        video.refresh();
    }


    public void runAsyncTask() {
        final Instructions instructions = Instructions.create(this);
        final Processor processor = new Processor(this, instructions);
        asyncTask = new AsyncTask(processor);
        asyncTask.start();
    }


    public void stopAsyncTask() {
        if (asyncTask != null)
            asyncTask.stop();

        asyncTask = null;
    }

    public Sound sound() {
        return sound;
    }

    public Video video() {
        return video;
    }

    public Memory memory() {
        return memory;
    }

    public Register register() {
        return register;
    }

    public KeyBoard keyBoard() {
        return keyBoard;
    }

    @Override
    public Timer timer() {
        return timer;
    }

}
