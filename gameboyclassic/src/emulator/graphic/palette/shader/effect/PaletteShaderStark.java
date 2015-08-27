package emulator.graphic.palette.shader.effect;

import emulator.graphic.palette.shader.PaletteShaderImpl;

/**
 * Created by vicboma on 22/08/15.
 */
public class PaletteShaderStark extends PaletteShaderImpl {

    public static Color P1 = new Color(255,255,255);
    public static Color P2 = new Color(179,179,179);
    public static Color P3 = new Color(118,118,118);
    public static Color P4 = new Color(0,0,0);

    public static PaletteShaderStark Create() {
        return new PaletteShaderStark(P1,P2,P3,P4);
    }

    public PaletteShaderStark(Color p1, Color p2, Color p3, Color p4) {
        super(p1,p2,p3,p4);
    }
}