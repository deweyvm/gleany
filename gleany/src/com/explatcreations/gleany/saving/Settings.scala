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

package com.explatcreations.gleany.saving

import com.explatcreations.gleany.Debug
import com.explatcreations.gleany.input.JoypadButton
import com.explatcreations.gleany.data.Point2i
import com.explatcreations.gleany.graphics.display.DisplayType
import scala.collection.mutable.ArrayBuffer


class Settings(controls: ControlNameCollection[ControlName], defaults: SettingDefaults) extends VideoSettings with AudioSettings {
  import SettingUtils.scalaMapToJava

  Debug.load()
  private val controlListeners = ArrayBuffer[() => Unit]()
  //fixme -- private these?
  val utils = new SettingUtils(controls, defaults)
  val filename = "settings.json"
  val raw: RawSettings = LoadUtils.load(classOf[RawSettings], filename, () => utils.makeNew, utils.verify)

  def addListener(listener: () => Unit) {
    controlListeners += listener
  }

  override def getDisplayType: DisplayType = DisplayType.fromInt(raw.displayType)
  override def setDisplayType(`type`: DisplayType) {
    raw.displayType = `type`.toInt
    flush()
  }

  override def getWindowSize: Point2i = Point2i(raw.width.toInt, raw.height.toInt)
  override def setWindowSize(width: Int, height: Int) {
    raw.width = width
    raw.height = height
    flush()
  }

  override def getSfxVolume: Float = raw.sfxVolume
  override def setSfxVolume(value: Float) {
    raw.sfxVolume = value
    flush()
  }

  override def getMusicVolume: Float = raw.musicVolume
  override def setMusicVolume(value: Float) {
    raw.musicVolume = value
    flush()
  }

  def getKey(control: ControlName): Int = raw.keyboardControls.get(control.name).toInt
  def setKey(control: ControlName, key: Int) {
    raw.keyboardControls.put(control.name, key)
    flush()
  }

  def getJoyButton(control: ControlName): JoypadButton = JoypadButton.fromString(raw.joypadControls.get(control.name))
  //fixme error handling
  def setJoyButton(control: ControlName, button: JoypadButton) {
    raw.joypadControls.put(control.name, button.toString)
    flush()
  }

  def resetControls() {
    raw.joypadControls = scalaMapToJava(controls.makeJoypadDefault)
    raw.keyboardControls = scalaMapToJava(controls.makeKeyboardDefault)
    controlListeners foreach {
      _()
    }
    flush()
  }

  def flush() {
    //fixme -- flush should verify first
    LoadUtils.flush(raw, filename)
  }
}
