package emulator.specification.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vicboma on 07/07/14.
 */
public class Video {
    public static final int WIDTH = 128;
    public static final int HEIGHT = 64;

    public static final int X_OFFSET = 65;
    public static final int Y_OFFSET = 20;

    private Graphics graphics;
    private Display display;
    private Screen screen;
    private BufferPoolImage bufferPoolImage;

    // Nos permite hacer lo mismo que el JPanel pero sin pintar pixeles.
    // No extendemos la clase, sino que la componemos.
    private JComponent jComponent;

    public Video(Display display, Screen screen, BufferPoolImage bufferPoolImage, JComponent component) {
        this.bufferPoolImage = bufferPoolImage;
        this.jComponent = component;
        this.display = display;
        final Image image = this.jComponent.createImage(display);
        this.screen = screen.set(image);
        this.graphics = this.jComponent.getGraphics();
    }

    public void refresh() {
        if (graphics == null)
            graphics = this.jComponent.getGraphics();

        this.bufferPoolImage.begin();
        this.bufferPoolImage.draw();
        this.bufferPoolImage.end(graphics);
    }

    public void scale(Integer factor) {
        this.screen.setSize(WIDTH * factor, HEIGHT * factor);
    }

    public Display display() {
        return display;
    }

    public JComponent component() {
        return jComponent;
    }
}
