package emulator.graphic.palette.shader.effect;

import emulator.graphic.palette.shader.PaletteShaderImpl;

/**
 * Created by vicboma on 22/08/15.
 */
public class PaletteShaderLightYellow extends PaletteShaderImpl {

    public static Color P1 = new Color(255, 255, 163);
    public static Color P2 = new Color(209, 208, 118);
    public static Color P3 = new Color(148 , 148,  78);
    public static Color P4 = new Color(102 , 102,  48);


    public static PaletteShaderLightYellow Create() {
        return new PaletteShaderLightYellow(P1,P2,P3,P4);
    }

    public PaletteShaderLightYellow(Color p1, Color p2, Color p3, Color p4) {
        super(p1,p2,p3,p4);
    }
}
