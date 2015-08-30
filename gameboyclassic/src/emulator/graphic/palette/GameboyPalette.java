package emulator.graphic.palette;

import graphics.palette.shader.effect.PaletteShaderGreen;
import utils.Operation;
import utils.shell.Logger;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public class GameboyPalette {

    public static final int BACKGROUND_DATA = 0;
    public static final int OBJECT_DATA_0 = 4;
    public static final int OBJECT_DATA_1 = 8;

    public static final int DMG_CODE_COLOR = 228;
    public static final int DMG_RESET_COLOR = 0;

    public static final int COLOR_1 = 0;
    public static final int COLOR_2 = 1;
    public static final int COLOR_3 = 2;
    public static final int COLOR_4 = 3;

    public static final short BLUE  = 0x3;
    public static final short GREEN = 0xC;
    public static final short RED   = 0x30;
    public static final short ALPHA = 0xC0;

    public String name;
    private int keyARGB;

    private Map<Integer, Integer> mapRegisterColorRGB;


    public GameboyPalette(String name) {
        this.name = name;
        this.mapRegisterColorRGB = loadRegisterColor();
    }

    private List<Short> createPaletteDMG(int keyARGB){
        return new ArrayList<Short>() {
            {
               add((short) (keyARGB & BLUE));
               add((short)((keyARGB & GREEN) >> 2));
               add((short)((keyARGB & RED)   >> 4));
               add((short)((keyARGB & ALPHA) >> 6));
            }
        };
    }

    private static Hashtable<Integer, Integer> loadRegisterColor() {
        return new Hashtable() {
            {
                put(COLOR_1, PaletteShaderGreen.P1.getRGB());
                put(COLOR_2, PaletteShaderGreen.P2.getRGB());
                put(COLOR_3, PaletteShaderGreen.P3.getRGB());
                put(COLOR_4, PaletteShaderGreen.P4.getRGB());
            }
        };
    }

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

        Logger.Debug(sb.toString());
    }

    public int getARGBColour(int valueRGB) {
        return mapRegisterColorRGB.get(valueRGB);
    }

    /**
     * DMG_CODE_COLOR   = 228 = 0x11 10 01 00
     * 0x76543210       =       0x76 54 32 10
     * <p>
     * Bit 7-6 - Data for Dot Data 11 (Normally darkest color)
     * Bit 5-4 - Data for Dot Data 10
     * Bit 3-2 - Data for Dot Data 01
     * Bit 1-0 - Data for Dot Data 00 (Normally lightest color)
     */
    public void createPalette(int keyARGB) {

        if (!validatePalette(keyARGB))
            return;

        StringBuffer sb = new StringBuffer("Create Palette " + name + " : ");

        final List<Short> paletteDMG = createPaletteDMG(keyARGB);
        IntStream.range(0, paletteDMG.size())
                 .boxed()
                 .forEach(x -> {
                     final Integer key = (int)paletteDMG.get(x);
                     final Integer value = loadRegisterColor().get(key);
                     sb.append(Operation.hexShort(value) + " ");
                     this.mapRegisterColorRGB.put(key,value);
                 });

        Logger.Debug(sb.toString());
    }

    private boolean validatePalette(int keyARGB) {
        this.keyARGB = keyARGB;
        return (DMG_CODE_COLOR == keyARGB &&  DMG_RESET_COLOR != keyARGB );
    }



}
