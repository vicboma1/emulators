package emulator;

import emulator.specification.SpecificationChip8Impl;
import emulator.specification.input.KeyBoard;
import framework.windows.Windows;

import javax.swing.*;


/**
 * Created by vicboma on 02/07/14.
 */
public class Chip8 {
    private KeyBoard keyBoard;
    private SpecificationChip8Impl specificationChip8Impl;
    private Windows windows;

    public Chip8() {
        keyBoard = new KeyBoard();
        specificationChip8Impl = new SpecificationChip8Impl(keyBoard);
        windows = Windows.create(specificationChip8Impl, keyBoard);
    }

    public JFrame windows() {
        return windows.component();
    }
}
