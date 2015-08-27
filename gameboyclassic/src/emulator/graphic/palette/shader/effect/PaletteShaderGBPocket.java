package emulator.graphic.palette.shader.effect;

import emulator.graphic.palette.shader.PaletteShaderImpl;

/**
 * Created by vicboma on 22/08/15.
 */
public class PaletteShaderGBPocket extends PaletteShaderImpl {

    public static Color P1 = new Color(227,231,205);
    public static Color P2 = new Color(196,197,169);
    public static Color P3 = new Color(142,139,102);
    public static Color P4 = new Color(108,108,81);


    public static PaletteShaderGBPocket Create() {
        return new PaletteShaderGBPocket(P1,P2,P3,P4);
    }

    public PaletteShaderGBPocket(Color p1, Color p2, Color p3, Color p4) {
        super(p1,p2,p3,p4);
    }
}
