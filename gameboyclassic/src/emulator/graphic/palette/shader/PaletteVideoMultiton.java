package emulator.graphic.palette.shader;

import graphics.game.palette.shader.backlight.*;
import graphics.game.palette.shader.effect.*;
import toolBar.video.menuItems.CheckboxMenuItemsBacklightList;
import toolBar.video.menuItems.CheckboxMenuItemsShaderList;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 25/08/15.
 */
public class PaletteVideoMultiton {

    private Map<String, PaletteVideo> paletteList;

    public PaletteVideoMultiton() {
        paletteList = loadPaletteShader();
    }

    private static Map<String, PaletteVideo> loadPaletteShader(){
        return new Hashtable() {
            final PaletteVideoDarkYellow create1 = PaletteVideoDarkYellow.Create();
            final PaletteVideoGreen create = PaletteVideoGreen.Create();
            final PaletteVideoGrey create2 = PaletteVideoGrey.Create();
            final PaletteVideoGBPocket create3 = PaletteVideoGBPocket.Create();
            final PaletteVideoLightYellow create4 = PaletteVideoLightYellow.Create();
            final PaletteVideoStark create5 = PaletteVideoStark.Create();
            final PaletteBackligthOrange create6 =  PaletteBackligthOrange.Create();
            final PaletteBackligthGreen create7 =  PaletteBackligthGreen.Create();
            final PaletteBackligthPink create8 =  PaletteBackligthPink.Create();
            final PaletteBackligthTeal create9 =  PaletteBackligthTeal.Create();
            final PaletteBackligthWhiteBlue create10 =  PaletteBackligthWhiteBlue.Create();
            final PaletteBackligthBlue create11 =  PaletteBackligthBlue.Create();
            final PaletteBackligthRed create12 =  PaletteBackligthRed.Create();

            {
                put(CheckboxMenuItemsShaderList.GREEN_SCALE, create);
                put(CheckboxMenuItemsShaderList.DARK_YELLOW_SCALE, create1);
                put(CheckboxMenuItemsShaderList.GREY_SCALE, create2);
                put(CheckboxMenuItemsShaderList.GB_POCKET_SCALE, create3);
                put(CheckboxMenuItemsShaderList.LIGHT_YELLOW_SCALE, create4);
                put(CheckboxMenuItemsShaderList.GREY_STARK_SCALE, create5);
                put(CheckboxMenuItemsBacklightList.ORANGE, create6);
                put(CheckboxMenuItemsBacklightList.GREEN, create7);
                put(CheckboxMenuItemsBacklightList.PINK, create8);
                put(CheckboxMenuItemsBacklightList.TEAL, create9);
                put(CheckboxMenuItemsBacklightList.WHITE_BLUE, create10);
                put(CheckboxMenuItemsBacklightList.BLUE, create11);
                put(CheckboxMenuItemsBacklightList.RED, create12);
            }
        };
    }

    public PaletteVideo get(String key){
        return paletteList.get(key);
    }

}
