package emulator.graphic.palette;

import java.util.List;

/**
 * Created by vicboma on 30/08/15.
 */
public interface DMGPalette {

    void setARGBColours(List<Integer> listARGBColor);
    int getARGBColour(int valueRGB);
    void create(int keyARGB);
}
