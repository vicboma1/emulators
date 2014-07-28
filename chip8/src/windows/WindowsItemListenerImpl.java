package windows;

import emulator.specification.SpecificationChip8Impl;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by vicboma on 09/07/14.
 */
public class WindowsItemListenerImpl implements ItemListener {

    private SpecificationChip8Impl specificationChip8Impl;
    private JCheckBoxMenuItem sound;

    WindowsItemListenerImpl(final SpecificationChip8Impl specificationChip8Impl, final JCheckBoxMenuItem sound) {
        this.specificationChip8Impl = specificationChip8Impl;
        this.sound = sound;
        this.sound.addItemListener(this);
    }

    public static ItemListener create(final SpecificationChip8Impl specificationChip8Impl, final JCheckBoxMenuItem sound) {
        return new WindowsItemListenerImpl(specificationChip8Impl, sound);
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        final Object itemSelected = event.getItem();

        if (itemSelected == this.sound) {
            //this.cpu.soundOff = sound.getState();
        }
    }
}
