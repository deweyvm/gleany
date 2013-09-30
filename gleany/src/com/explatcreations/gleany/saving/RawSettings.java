/******************************************************************************
 * Copyright 2013, deweyvm
 *
 * This file is part of Gleany.
 *
 * Gleany is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Gleany is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along
 * with Gleany.
 *
 * If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

package com.explatcreations.gleany.saving;

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
