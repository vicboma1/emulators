package emulator.graphic.stat;

/**
 * Created by vicboma on 21/08/15.
 */
public class StatVideoImpl implements StatVideo {

    private boolean lyc;
    private boolean oam;
    private boolean hblank;
    private boolean vblank;
    private byte	mode;

    public static StatVideo Create(){ return new StatVideoImpl(false,false,false,false,(byte)0);}
    /**
     *
     * @param statLyc
     * @param statOam
     * @param statHblank
     * @param statVblank
     * @param statMode
     */
    public StatVideoImpl(boolean statLyc, boolean statOam, boolean statHblank, boolean statVblank, byte statMode) {
        this.lyc = statLyc;
        this.oam = statOam;
        this.hblank = statHblank;
        this.vblank = statVblank;
        this.mode = statMode;
    }


    @Override
    public boolean isStatLyc() {
        return lyc;
    }

    @Override
    public void setStatLyc(boolean statLyc) {
        this.lyc = statLyc;
    }

    @Override
    public boolean isStatOam() {
        return oam;
    }

    @Override
    public void setStatOam(boolean statOam) {
        this.oam = statOam;
    }

    @Override
    public boolean isStatHblank() {
        return hblank;
    }

    @Override
    public void setStatHblank(boolean statHblank) {
        this.hblank = statHblank;
    }

    @Override
    public boolean isStatVblank() {
        return vblank;
    }

    @Override
    public void setStatVblank(boolean statVblank) {
        this.vblank = statVblank;
    }

    @Override
    public byte getStatMode() {
        return mode;
    }

    @Override
    public void setStatMode(byte statMode) {
        this.mode = statMode;
    }
}

