package emulator.graphic.stat;

/**
 * Created by vicboma on 06/09/15.
 */
public interface StatVideo {
    boolean isStatLyc();

    void setStatLyc(boolean statLyc);

    boolean isStatOam();

    void setStatOam(boolean statOam);

    boolean isStatHblank();

    void setStatHblank(boolean statHblank);

    boolean isStatVblank();

    void setStatVblank(boolean statVblank);

    byte getStatMode();

    void setStatMode(byte statMode);
}
