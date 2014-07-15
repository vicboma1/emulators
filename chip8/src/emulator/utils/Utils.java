package emulator.utils;

import emulator.specification.graphics.Display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vicboma on 08/07/14.
 */
public class Utils {
    public static final boolean FALSE = false;
    public static final int ZERO = 0x0;
    private static final int HALF = 0x02;

    private static DisplayMode displayMode() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
    }

    public static Integer DisplayModeCenterX() {
        final int width = displayMode().getWidth();
        final int centerX = (width / HALF) - (Display.WIDTH / HALF);
        return centerX;
    }

    public static Integer DisplayModeCenterY() {
        final int height = displayMode().getHeight();
        final int centerY = (height / HALF) - (Display.HEIGHT / HALF);
        return centerY;
    }

    public static void setLookAndFeel(String lookAndFeel, Component component) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (InstantiationException |
                ClassNotFoundException |
                UnsupportedLookAndFeelException |
                IllegalAccessException ex) {
            ex.printStackTrace();
        } finally {
            SwingUtilities.updateComponentTreeUI(component);
        }
    }

    public static int mergeTo16bits(short big, short little) {
        return ((big << 8) | little);
    }
}
