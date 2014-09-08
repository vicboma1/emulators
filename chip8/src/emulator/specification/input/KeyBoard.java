package emulator.specification.input;

import framework.dispose.Disposable;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by vicboma on 08/07/14.
 */
public class KeyBoard implements Disposable {

    private KeyBoardStatus keyBoardStatus;
    private KeyBoardListener keyBoardListener;
    private List<Boolean> keyPressed;

    public KeyBoard() {
        initializeKeyPressed();
        keyBoardStatus = new KeyBoardStatus();
        keyBoardListener = new KeyBoardListener(this);
    }

    private void initializeKeyPressed() {
        keyPressed = null;
        keyPressed = new ArrayList();
        IntStream stream = IntStream.range(0, 16);
        stream.sequential().forEach(x -> this.keyPressed.add(x, false));
    }

    @Override
    public void dispose() {
        initializeKeyPressed();
        this.keyBoardListener.initializeMapEvent();
    }

    public KeyListener listener() {
        return this.keyBoardListener.listener();
    }

    public Boolean get(final int index) {
        return this.keyPressed.get(index);
    }

    public Boolean set(final int index, Boolean value) {
        return this.keyPressed.set(index, value);
    }

    public KeyBoardStatus status() {
        return this.keyBoardStatus;
    }
}
