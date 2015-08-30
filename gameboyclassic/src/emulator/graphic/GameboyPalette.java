package emulator.graphic;

import java.util.List;

/**
 * Created by vicboma on 30/08/15.
 */
public interface GameboyPalette {

    void setARGBColours(List<Integer> listARGBColor);
    int getARGBColour(int valueRGB);
    void createPalette(int keyARGB);
}
