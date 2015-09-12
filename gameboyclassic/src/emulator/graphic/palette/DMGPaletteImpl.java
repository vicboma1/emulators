package emulator.graphic.palette;

import graphics.game.palette.shader.effect.PaletteVideoGreen;
import utils.Operation;
import utils.shell.Logger;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Value	Pixel	Emulated colour
 * 0     	Off	    [255, 255, 255]
 * 1	    33% on	[192, 192, 192]
 * 2       66% on	[96, 96, 96]
 * 3 	    On   	[0, 0, 0]
 * <p>
 * Table 2: Colour reference values Background palette
 */
public class DMGPaletteImpl implements DMGPalette {

    public static final int ATTRIBUTE_TABLE_COLOR = 1;
    public static final int PATTERN_TABLE_ENTRY = 2;
    public static final int ATTRIBUTE_TABLE_ENTRY = 2;

    public static final int BACKGROUND_DATA = 0x0;
    public static final int OBJECT_DATA_0 = 0x4;
    public static final int OBJECT_DATA_1 = 0x8;

    public static final int DMG_CODE_COLOR_BACKGROUND = 0xE4;
    public static final int DMG_CODE_COLOR_OBJECT_1 = 0x54;

    public static final int DMG_RESET_COLOR = 0x0;

    public static final int COLOR_1 = 0x0;
    public static final int COLOR_2 = 0x1;
    public static final int COLOR_3 = 0x2;
    public static final int COLOR_4 = 0x3;

    public static final short ALPHA = 0x3;
    public static final short GREEN = 0xC;
    public static final short RED = 0x30;
    public static final short BLUE = 0xC0;

    private String name;
    private int keyARGB;

    private Map<Integer, Integer> mapRegisterColorRGB;


    public DMGPaletteImpl(String name) {
        this.name = name;
        this.mapRegisterColorRGB = loadRegisterColor();
    }

    private List<Short> createPaletteDMG(int keyARGB) {
        return new ArrayList<Short>() {
            {
                add((short) (keyARGB & ALPHA));
                add((short) ((keyARGB & GREEN) >> 2));
                add((short) ((keyARGB & RED) >> 4));
                add((short) ((keyARGB & BLUE) >> 6));
            }
        };
    }

    private static Hashtable<Integer, Integer> loadRegisterColor() {
        return new Hashtable() {
            {
                put(COLOR_1, PaletteVideoGreen.P1.getRGB());
                put(COLOR_2, PaletteVideoGreen.P2.getRGB());
                put(COLOR_3, PaletteVideoGreen.P3.getRGB());
                put(COLOR_4, PaletteVideoGreen.P4.getRGB());
            }
        };
    }

    // PaletteBackligthOrange

    public void setARGBColours(List<Integer> listARGBColor) {

        if (!validatePalette(keyARGB))
            return;

        StringBuffer sb = new StringBuffer("Set shader " + name + " : ");
        final int size = listARGBColor.size();
        IntStream.range(0, size)
                .boxed()
                .forEach(x -> {
                    final Integer value = listARGBColor.get(x);
                    sb.append(Operation.hexShort(value) + " ");
                    mapRegisterColorRGB.put(x, value);
                });

        Logger.DebugToolBar(sb.toString());
    }


    public int getARGBColour(int valueRGB) {
        return mapRegisterColorRGB.get(valueRGB);
    }

    /**
     * DMG_CODE_COLOR   = 228 = 0x11 10 01 00
     * 0x76543210       =       0x76 54 32 10
     * <p>
     * Bit 7-6 - Data for Dot Data 11 (Normally darkest color) Red
     * Bit 5-4 - Data for Dot Data 10 Green
     * Bit 3-2 - Data for Dot Data 01 Blue
     * Bit 1-0 - Data for Dot Data 00 (Normally lightest color) Alpha
     */
    public void create(int keyARGB) {

        if (!validatePalette(keyARGB))
            return;

        final List<Short> paletteDMG = createPaletteDMG(keyARGB);
        IntStream.range(0, paletteDMG.size())
                .boxed()
                .forEach(x -> {
                    final Integer key = (int) paletteDMG.get(x);
                    final Integer value = (this.mapRegisterColorRGB != null)
                            ? this.mapRegisterColorRGB.get(key)
                            : loadRegisterColor().get(key);
                    this.mapRegisterColorRGB.put(key, value);
                });

    }

    private boolean validatePalette(int keyARGB) {
        this.keyARGB = keyARGB;
        return (DMG_RESET_COLOR != keyARGB);
    }


}
