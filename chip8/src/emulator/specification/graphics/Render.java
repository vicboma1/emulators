package emulator.specification.graphics;

import emulator.utils.Utils;

import java.util.stream.IntStream;

/**
 * Created by vicboma on 14/07/14.
 */
public class Render {

    private Video video;
    private short[] texelBitLine;
    private int x;
    private int y;

    public static Render create(Video video, short[] texelBitLine, int x, int y) {
        return new Render(video,texelBitLine,x,y);
    }

    public static Render create(Video video, short[] texelBitLine, int x, int y) {
        return new Render(video,texelBitLine,x,y);
    }    /**
     * Proceso optimizado de pintado y deteccion de colision
     *
     * @param video
     * @param texelBitLine
     * @param x
     * @param y
     * @return
     */
    public Render(Video video, short[] texelBitLine, int x, int y) {
        this.video = video;
        this.texelBitLine = texelBitLine;
        this.x = x;
        this.y = y;
    }

    public Boolean binaryMatrix() {
        final boolean [] collision = {false};
        final int posX = 2 * x;
        final int posY = 2 * y;

        IntStream stream = IntStream.range(Utils.ZERO,texelBitLine.length);
        stream.sequential().forEach(z -> {
            final int offsetX = 2 * z;
            final short isActive = texelBitLine[z];
            if (isActive == 1) {
                final int width = Video.WIDTH;
                final int index = width * posY + (posX + offsetX) % width;

                if (video.display().matrix(index) == Display.FOREGROUND) {
                    paintGraphicMatrix(index, Display.BACKGROUND);
                    collision[0] = true;
                }
                else
                    paintGraphicMatrix(index,Display.FOREGROUND );
            }
        });

        return collision[0];
    }

    private void paintGraphicMatrix(Integer index, Integer color) {
        video.display().matrix(index, color);
    }
}
