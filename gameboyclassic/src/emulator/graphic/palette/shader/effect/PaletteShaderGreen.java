package emulator.graphic.palette.shader.effect;

import emulator.graphic.palette.shader.PaletteShaderImpl;

/**
 * Created by vicboma on 22/08/15.
 */
public class PaletteShaderGreen extends PaletteShaderImpl {

    public static Color P1 = new Color(181,220,75);
    public static Color P2 = new Color(134,168,55);
    public static Color P3 = new Color(44,96,54);
    public static Color P4 = new Color(1 ,56,17);

    public static PaletteShaderGreen Create() {
        return new PaletteShaderGreen(P1,P2,P3,P4);
    }


    public PaletteShaderGreen(Color p1, Color p2, Color p3, Color p4) {
        super(p1,p2,p3,p4);
    }
}
