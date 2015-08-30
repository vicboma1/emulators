package emulator.graphic.palette.shader.effect;

import graphics.palette.shader.PaletteShaderImpl;

import java.awt.*;

/**
 * Created by vicboma on 22/08/15.
 */
public class PaletteShaderGrey extends PaletteShaderImpl {

    public static Color P1 = new Color(240,240,240);
    public static Color P2 = new Color(179,179,179);
    public static Color P3 = new Color(118,118,118);
    public static Color P4 = new Color(56,56,56);

    public static PaletteShaderGrey Create() {
        return new PaletteShaderGrey(P1,P2,P3,P4);
    }

    public PaletteShaderGrey(Color p1, Color p2, Color p3, Color p4) {
        super(p1,p2,p3,p4);
    }
}
