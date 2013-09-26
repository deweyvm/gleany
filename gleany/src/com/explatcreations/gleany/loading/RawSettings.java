package com.explatcreations.gleany.loading;

import com.explatcreations.gleany.data.Point2i;

import java.util.Map;

public class RawSettings {

    public Map<String, Float> keyboardControls;
    public Map<String, String> joypadControls;
    public float sfxVolume;
    public float musicVolume;
    public int displayType;
    public float width;
    public float height;


    public static RawSettings makeNew(Map<String, Float> keyboardDefault,
                                      Map<String, String> joypadDefault,
                                      Point2i windowSize,
                                      int displayType,
                                      float musicVolume,
                                      float sfxVolume) {
        final RawSettings result = new RawSettings();
        result.keyboardControls = keyboardDefault;
        result.joypadControls = joypadDefault;
        result.sfxVolume = sfxVolume;
        result.musicVolume = musicVolume;
        result.width = windowSize.x();
        result.height = windowSize.y();
        result.displayType = displayType;
        return result;
    }
}
