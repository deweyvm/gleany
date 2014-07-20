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

package com.deweyvm.gleany.audio

import com.badlogic.gdx.audio

class Sfx(manager: AudioManager, sound: audio.Sound) extends AudioInstance(manager) {
  private var adjVolume = 1f
  private var id:Option[Long] = None
  override def play() {
    //stop()
    //manager += this
    id = Some(sound.play(adjVolume))
  }

  def setAdjustVolume(adjVol: Float) {
    adjVolume = adjVol
    setVolume(manager.settings.getSfxVolume)
  }

  override def isPlaying: Boolean = ???


  override def setVolume(newVolume: Float) {
    id foreach { i => sound.setVolume(i, newVolume * adjVolume) }
  }

  override def pause() {
    id foreach { i => sound.pause(i) }
  }

  override def stop() {
    id.foreach { i => sound.stop(i) }
  }

  override def resume() {

    id.foreach { i => sound.resume(i) }

  }

  setVolume(manager.settings.getSfxVolume)
}
