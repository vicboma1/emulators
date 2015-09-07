package emulator.graphic.palette.shader;

import java.awt.*;

/**
 * Created by vicboma on 22/08/15.
 */
public class PaletteVideoImpl implements PaletteVideo {

    protected Color p1;
    protected Color p2;
    protected Color p3;
    protected Color p4;
    protected Color bezel;

    public PaletteVideoImpl(Color p1, Color p2, Color p3, Color p4, Color bezel) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.bezel = bezel;
    }

    @Override
    public int palette1Rgb() { return this.p1.getRGB();}
    @Override
    public int palette2Rgb() { return this.p2.getRGB();}
    @Override
    public int palette3Rgb() { return this.p3.getRGB();}
    @Override
    public int palette4Rgb() { return this.p4.getRGB();}
    @Override
    public Color bezel() { return this.bezel;  }


}
