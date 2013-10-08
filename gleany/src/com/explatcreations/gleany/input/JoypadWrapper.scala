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

package com.explatcreations.gleany.input

import com.badlogic.gdx.controllers.{PovDirection, ControlType, ControllerAdapter, Controller}
import collection.mutable
import triggers.JoypadHelper
import com.badlogic.gdx.controllers.desktop.HackGetPovs

object JoypadWrapper {
  object Axes {
    val horizontalAxis: Int = 1
    val verticalAxis: Int = 0
    abstract class Axis(val sign:Int, val code: Int)
    private[Axes] class Horizontal(sign: Int) extends Axis(sign, horizontalAxis)
    private[Axes] class Vertical(sign: Int) extends Axis(sign, verticalAxis)
    val Left: Axis = new Horizontal(1)
    val Right: Axis = new Horizontal(-1)
    val Up: Axis = new Vertical(-1)
    val Down: Axis = new Vertical(1)
  }
  def getAxisList(value: PovDirection): List[Axes.Axis] = {
    value match {
      case PovDirection.center => List()
      case PovDirection.east => List(Axes.Right)
      case PovDirection.west => List(Axes.Left)
      case PovDirection.north => List(Axes.Up)
      case PovDirection.south => List(Axes.Down)
      case PovDirection.northEast => getAxisList(PovDirection.north) ++ getAxisList(PovDirection.east)
      case PovDirection.northWest => getAxisList(PovDirection.north) ++ getAxisList(PovDirection.west)
      case PovDirection.southEast => getAxisList(PovDirection.south) ++ getAxisList(PovDirection.east)
      case PovDirection.southWest => getAxisList(PovDirection.south) ++ getAxisList(PovDirection.west)
    }
  }
}

class JoypadWrapper(controller: Controller) {
  private val buttonState = new mutable.HashMap[Int, Boolean]().withDefault(_ => false)
  private val axisState = new mutable.HashMap[Int, Int]().withDefault(_ => 0)

  private val hasPov = checkPovs

  private def checkPovs = {
    val numPovs = HackGetPovs.getPovCount(controller)
    numPovs > 0
  }

  private val listener = new ControllerAdapter {
    override def connected(controller: Controller) {}

    override def disconnected(controller: Controller) {}

    override def buttonUp(controller: Controller, buttonCode: Int): Boolean = {
      buttonState(buttonCode) = false
      false
    }

    override def buttonDown(controller: Controller, buttonCode: Int): Boolean = {
      buttonState(buttonCode) = true
      false
    }

    override def axisMoved(controller: Controller, axisCode: Int, value: Float): Boolean = {
      if (hasPov) {
        return false
      }
      axisState(axisCode) = JoypadHelper.round(value)
      false
    }

    override def povMoved (controller: Controller, povIndex: Int, value: PovDirection): Boolean = {
      axisState.clear()

      getAxisList(value) foreach { axis: JoypadWrapper.Axes.Axis =>
        axisState(axis.code) = axis.sign
      }
      false
    }

  }
  controller.addListener(listener)

  def isButtonPressed(code: Int): Boolean = buttonState(code)

  def isAxisPressed(code: Int): Float = axisState(code)

}
