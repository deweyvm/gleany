/** ****************************************************************************
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
  * ****************************************************************************/

package com.explatcreations.gleany.audio

import com.badlogic.gdx.audio

class Sfx(manager: AudioManager, sound: audio.Music, looped: Boolean) extends AudioInstance(manager) {
  private var adjVolume = 1f

  override def play() {
    manager += this
    sound.setLooping(looped)
    sound.play()
  }

  def setAdjustVolume(adjVol: Float) {
    adjVolume = adjVol
    setVolume(manager.settings.getSfxVolume)
  }

  override def isPlaying = sound.isPlaying


  override def setVolume(newVolume: Float) {
    sound.setVolume(newVolume * adjVolume)
  }

  override def pause() {
    sound.pause()
  }

  override def stop() {
    sound.stop()
  }

  override def resume() {
    sound.play()
  }

  setVolume(manager.settings.getSfxVolume)
}
