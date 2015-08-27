package emulator.graphic.palette.shader;

import emulator.graphic.palette.shader.effect.*;
import toolBar.video.ToolBarVideoImpl;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by vicboma on 25/08/15.
 */
public class PaletteShaderMultiton {

    private Map<String, PaletteShader> paletteList;

    public PaletteShaderMultiton() {
        paletteList = loadPaletteShader();
    }

    private static Map<String, PaletteShader> loadPaletteShader(){
        return new Hashtable() {
            final PaletteShaderDarkYellow create1 = PaletteShaderDarkYellow.Create();
            final PaletteShaderGreen create = PaletteShaderGreen.Create();
            final PaletteShaderGrey create2 = PaletteShaderGrey.Create();
            final PaletteShaderGBPocket create3 = PaletteShaderGBPocket.Create();
            final PaletteShaderLightYellow create4 = PaletteShaderLightYellow.Create();
            final PaletteShaderStark create5 = PaletteShaderStark.Create();
            {
                put(ToolBarVideoImpl.GREEN_SCALE, create);
                put(ToolBarVideoImpl.DARK_YELLOW_SCALE, create1);
                put(ToolBarVideoImpl.GREY_SCALE, create2);
                put(ToolBarVideoImpl.GB_POCKET_SCALE, create3);
                put(ToolBarVideoImpl.LIGHT_YELLOW_SCALE, create4);
                put(ToolBarVideoImpl.GREY_STARK_SCALE, create5);
            }
        };
    }

    public PaletteShader get(String key){
        return paletteList.get(key);
    }

}
