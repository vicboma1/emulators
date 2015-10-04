package main;

import main.configuration.*;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by vicboma on 03/06/14.
 */
public class GameBoy {

    public static final String VICBOMA_GAME_BOY_EMULATOR_V1 = "Vicboma  -  Game Boy Emulator v";
    public static String VERSION = "0.32.0 Debug";
    public static final String C_2014_VICTOR_MANUEL_BOLINCHES_MARIN = " (c) 2014 Victor Manuel Bolinches Marin";
    public static Boolean DEBUG = true;

    /**
     * Main
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        //Logger 4 java
        BasicConfigurator.configure();
        //Emulator Gameboy
        EmulatorConfigurator.configure();
    }
}
