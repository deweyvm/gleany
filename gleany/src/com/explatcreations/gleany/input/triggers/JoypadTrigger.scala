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

package com.explatcreations.gleany.input.triggers

import com.explatcreations.gleany.input.{FaceButton, AxisButton, JoypadButton}


class JoypadTrigger(val button: JoypadButton) extends Trigger {
    import JoypadHelper._
    override def isPressed = button.info match {
        case AxisButton(code, value) => controller exists { _.isAxisPressed(code) == value }
        case FaceButton(code) => controller exists { _.isButtonPressed(code) }
    }

}