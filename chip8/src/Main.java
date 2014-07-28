import emulator.Chip8;
import emulator.utils.Utils;

import javax.swing.*;

/**
 * Created by vicboma on 08/07/14.
 */
public class Main {

    private static final String SYSTEM_LOOK_AND_FEEL_CLASS_NAME = UIManager.getSystemLookAndFeelClassName();
    private static final String EMULATOR_LOADED = "Emulator loaded ";
    private static final String EMULATOR_LOADED_KO = EMULATOR_LOADED + "KO!";
    private static final String EMULATOR_LOADED_OK = EMULATOR_LOADED + "OK!";

    public static void main(String[] args) {
        final Chip8 chip8 = new Chip8();
        try {
            Utils.setLookAndFeel(SYSTEM_LOOK_AND_FEEL_CLASS_NAME, chip8.windows());
        } catch (Exception e) {
            System.out.println(EMULATOR_LOADED_KO);
            e.printStackTrace();
        } finally {
            System.out.println(EMULATOR_LOADED_OK);
        }
    }
}
