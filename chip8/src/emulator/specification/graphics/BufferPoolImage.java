package emulator.specification.graphics;

import emulator.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by vicboma on 08/07/14.
 */
public class BufferPoolImage {
    private BufferedImage bufferedImage;
    private Screen screen;

    public BufferPoolImage(Screen screen) {
        this.screen = screen;
    }

    public void begin() {
        this.bufferedImage = new BufferedImage(Video.WIDTH, Video.HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public void draw() {
        screen.flush();
        //internamente llama al consumidor de imagenes cuando hacemos esta llamada
        this.bufferedImage.getGraphics().drawImage(screen.image(), Utils.ZERO, Utils.ZERO, null);
    }

    public void end(Graphics graphics) {
        graphics.drawImage(bufferedImage, Video.X_OFFSET, Video.Y_OFFSET, screen.width(), screen.height(), Color.black, null);
    }
}
