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

package com.explatcreations.gleany.input

import com.badlogic.gdx.controllers.{ControllerAdapter, Controller}
import collection.mutable
import triggers.JoypadHelper

class JoypadWrapper(controller: Controller) {
    val buttonState = new mutable.HashMap[Int,Boolean]().withDefault(_ => false)
    val axisState = new mutable.HashMap[Int,Int]().withDefault(_ => 0)
    val listener = new ControllerAdapter {
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
            axisState(axisCode % 2 /*treat all dpads equally*/) = JoypadHelper.round(value)
            false
        }
    }
    controller.addListener(listener)

    def isButtonPressed(code: Int): Boolean = buttonState(code)

    def isAxisPressed(code: Int): Float = axisState(code)
}
