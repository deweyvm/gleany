package com.explatcreations.gleany.saving

import java.util
import com.explatcreations.gleany.data.Point2i
import com.badlogic.gdx.math.MathUtils

object SettingUtils {
    def scalaMapToJava[K,V](map:Map[K,V]):java.util.Map[K,V] = {
        val result = new util.HashMap[K,V]()
        for(k <- map.keys) {
            result.put(k, map(k))
        }
        result
    }
}

class SettingUtils(controls:ControlNameCollection[ControlName], defaults:SettingDefaults) {
    import SettingUtils.scalaMapToJava


    def makeNew = {
        RawSettings.makeNew(scalaMapToJava(controls.makeKeyboardDefault),
                            scalaMapToJava(controls.makeJoypadDefault),
                            defaults.WindowSize, //fixme get these from elsewhere
                            defaults.DisplayMode,
                            defaults.MusicVolume,
                            defaults.SfxVolume)
    }

    def verify(raw:RawSettings): RawSettings = {
        verify(raw, defaults.WindowSize)
    }

    private def verifyMap[T](raw: RawSettings,
                             map: java.util.Map[String, T],
                             makeDefault: () => java.util.Map[String, T]):java.util.Map[String, T] = {
        if (map == null) {
            return makeDefault()
        }
        //fixme -- dont need to use java types here anymore
        val foundNames: util.Collection[T] = new util.ArrayList[T]
        for (name <- controls.values) {
            val button: T = map.get(name.toString)
            if (button == null || foundNames.contains(button)) {
                return makeDefault()
            }
            foundNames.add(button)
        }
        map
    }

    def verify(raw: RawSettings, windowSize: Point2i): RawSettings = {
        if (raw.width < windowSize.x) {
            raw.width = windowSize.x
        }
        if (raw.height < windowSize.y) {
            raw.height = windowSize.y
        }
        raw.sfxVolume = MathUtils.clamp(raw.sfxVolume, 0, 1)
        raw.musicVolume = MathUtils.clamp(raw.musicVolume, 0, 1)
        if (raw.displayType < 0 || raw.displayType > 2) {
            raw.width = windowSize.x
            raw.height = windowSize.y
            raw.displayType = 0
        }
        raw.keyboardControls = verifyMap(raw, raw.keyboardControls, () => scalaMapToJava(controls.makeKeyboardDefault))
        raw.joypadControls = verifyMap(raw, raw.joypadControls, () => scalaMapToJava(controls.makeJoypadDefault))
        raw
    }
}
