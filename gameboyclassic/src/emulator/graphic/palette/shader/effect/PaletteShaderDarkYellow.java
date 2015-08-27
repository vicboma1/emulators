package emulator.graphic.palette.shader.effect;

import emulator.graphic.palette.shader.PaletteShaderImpl;

/**
 * Created by vicboma on 22/08/15.
 */
public class PaletteShaderDarkYellow extends PaletteShaderImpl {

    public static Color P1 = new Color(255, 243, 140);
    public static Color P2 = new Color(182, 173, 90);
    public static Color P3 = new Color(107 , 105,  57);
    public static Color P4 = new Color(33 , 32,  19);


    public static PaletteShaderDarkYellow Create() {
        return new PaletteShaderDarkYellow(P1,P2,P3,P4);
    }

    public PaletteShaderDarkYellow(Color p1, Color p2, Color p3, Color p4) {
        super(p1,p2,p3,p4);
    }
}
