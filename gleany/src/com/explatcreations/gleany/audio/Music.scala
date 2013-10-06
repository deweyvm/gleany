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

package com.explatcreations.gleany.audio

import com.badlogic.gdx.audio

class Music(manager: AudioManager, music: audio.Music) extends AudioInstance(manager) {
    private var adjVolume = 1f
    override def play() {
        manager += this
        music.play()
    }

    override def isPlaying = music.isPlaying

    def setAdjustVolume(adjVol: Float) {
        adjVolume = adjVol
        setVolume(manager.settings.getMusicVolume)
    }

    override def setVolume(newVolume: Float) {
        music.setVolume(newVolume*adjVolume)
    }

    override def stop() { }

    override def pause() {
        music.pause()
    }

    override def resume() {
        music.play()
    }

    setVolume(manager.settings.getMusicVolume)
    music.setLooping(true)
}
