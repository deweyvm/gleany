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

package com.explatcreations.gleany.graphics.display

import com.badlogic.gdx.Gdx
import org.lwjgl.opengl.{Display => LwjglDisplay}
import com.explatcreations.gleany.data.Point2i

object DisplayType {
  def fromInt(int: Int): DisplayType = Display.All(int) //fixme error handling
}

abstract class DisplayType(id: Int) {
  def toInt: Int = id
}

object Display {
  val All: IndexedSeq[DisplayType] = Vector(Windowed, Fullscreen, WindowedFullscreen)

  case object Windowed extends DisplayType(0)
  case object Fullscreen extends DisplayType(1)
  case object WindowedFullscreen extends DisplayType(2)

  def setDisplayMode(mode: DisplayType, defaultWindowSize: Point2i) {
    mode match {
      case Fullscreen =>
        val desktopMode = Gdx.graphics.getDesktopDisplayMode
        Gdx.graphics.setDisplayMode(desktopMode.width, desktopMode.height, true)
      case Windowed =>
        System.setProperty("org.lwjgl.opengl.Window.undecorated", "false")
        LwjglDisplay.setResizable(true)
        Gdx.graphics.setDisplayMode(defaultWindowSize.x, defaultWindowSize.y, false)
      case WindowedFullscreen =>
        val desktopMode = Gdx.graphics.getDesktopDisplayMode
        System.setProperty("org.lwjgl.opengl.Window.undecorated", "true")
        LwjglDisplay.setResizable(false)
        Gdx.graphics.setDisplayMode(desktopMode.width, desktopMode.height, false)
    }

  }
}
