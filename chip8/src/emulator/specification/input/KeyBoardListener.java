package emulator.specification.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 08/07/14.
 */
public class KeyBoardListener {

    private Map<Integer, Integer> mapEventInternal;
    private KeyListener keyListener;
    private KeyBoard keyBoard;

    public KeyBoardListener(KeyBoard keyBoard) {
        this.keyListener = keyListener();
        this.keyBoard = keyBoard;
        initializeMapEvent();
    }

    private KeyListener keyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                put(e, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                put(e, false);
            }
        };
    }

    private void put(KeyEvent e, Boolean value) {
        Integer index = null;
        final int keypress = e.getKeyCode();

        if (mapEventInternal.containsKey(keypress)) {
            index = mapEventInternal.get(keypress);
            this.keyBoard.set(index, value);
        }
    }

    public KeyListener listener() {
        return keyListener;
    }

    public void initializeMapEvent() {
        this.mapEventInternal = null;
        this.mapEventInternal = initialize();
    }

    private Hashtable initialize() {
        return new Hashtable() {
            {
                put(KeyEvent.VK_1, 0);
                put(KeyEvent.VK_2, 1);
                put(KeyEvent.VK_3, 2);
                put(KeyEvent.VK_4, 3);
                put(KeyEvent.VK_Q, 4);
                put(KeyEvent.VK_W, 5);
                put(KeyEvent.VK_E, 6);
                put(KeyEvent.VK_R, 7);
                put(KeyEvent.VK_A, 8);
                put(KeyEvent.VK_S, 9);
                put(KeyEvent.VK_D, 10);
                put(KeyEvent.VK_F, 11);
                put(KeyEvent.VK_Z, 12);
                put(KeyEvent.VK_X, 13);
                put(KeyEvent.VK_C, 14);
                put(KeyEvent.VK_V, 15);
            }
        };
    }
}
