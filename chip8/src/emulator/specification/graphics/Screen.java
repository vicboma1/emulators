package emulator.specification.graphics;

import java.awt.*;

/**
 * Created by vicboma on 08/07/14.
 */
public class Screen {

    private Image image;
    private int width;
    private int height;

    public Screen() {
    }

    public Screen set(Image image) {
        this.image = image;
        return this;
    }

    public void flush() {
        this.image.flush();
    }

    public void setSize(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer width() {
        return this.width;
    }

    public Integer height() {
        return this.height;
    }

    public Image image() {
        return this.image;
    }
}
