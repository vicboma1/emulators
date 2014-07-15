package emulator.specification.graphics;

/**
 *  Based on Game Boy Emulator.
 *  COPYRIGHT (C) 2001 David Winchurch.
 *
 * Created by vicboma on 03/07/14.
 */

import emulator.utils.Utils;

import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Display implements ImageProducer {

    public static final int FOREGROUND = 0xFFFFFFFF;
    public static final int BACKGROUND = 0x00000000;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 320;
    public static final int ZERO = Utils.ZERO;
    private static final int BITS = 32;
    private static final int RMASK = 0x00ff0000; // Red mask
    private static final int GMASK = 0x0000ff00; // Green mask
    private static final int BMASK = 0x000000ff; //Blue mask
    private static final int AMASK = 0x00000000; // Alpha mask
    // This color model is similar to an X11 TrueColor visual.
    // The default RGB ColorModel specified by the getRGBdefault
    // method is a DirectColorModel with the following parameters:
    private ColorModel model;

    private int[] matrix;
    private int width;
    private int height;
    private List<ImageConsumer> consumers;

    public Display(int width, int height) {
        this.consumers = new ArrayList<ImageConsumer>();
        this.model = ColorModel.getRGBdefault();
        this.width = width;
        this.height = height;
        this.matrix = new int[width * height];
        this.model = new DirectColorModel(BITS, RMASK, GMASK, BMASK, AMASK);
    }

    public void startProduction(ImageConsumer imageConsumer) {
        addConsumer(imageConsumer);
        this.consumers.stream().sequential().forEach(x -> {
            x.setPixels(ZERO, ZERO, width, height, model, matrix, ZERO, width);
            x.imageComplete(ImageConsumer.SINGLEFRAMEDONE);
        });
    }

    public synchronized void addConsumer(ImageConsumer imageConsumer) {
        if (!isConsumer(imageConsumer)) {
            imageConsumer.setHints(ImageConsumer.TOPDOWNLEFTRIGHT | ImageConsumer.SINGLEPASS);
            imageConsumer.setDimensions(width, height);
            imageConsumer.setProperties(new Hashtable());
            imageConsumer.setColorModel(model);
            this.consumers.add(imageConsumer);
        }
    }

    public synchronized boolean isConsumer(ImageConsumer imageConsumer) {
        return this.consumers.contains(imageConsumer);
    }

    public synchronized void removeConsumer(ImageConsumer imageConsumer) {
        this.consumers.remove(imageConsumer);
    }

    public void requestTopDownLeftRightResend(ImageConsumer imageConsumer) {
    }

    public Integer matrix(int index) {
        return matrix[index];
    }

    public void matrix(int index, int value) {
        matrix[index] = value;
    }
}
