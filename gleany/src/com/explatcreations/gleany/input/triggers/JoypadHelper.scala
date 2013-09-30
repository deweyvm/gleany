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

import com.badlogic.gdx.controllers.{Controller, Controllers}
import com.explatcreations.gleany.Debug
import com.explatcreations.gleany.input.JoypadWrapper

object JoypadHelper {
    private val rawController:Option[Controller] = {
        try {
            val allControllers = Controllers.getControllers
            allControllers.size match {
                case 0 => None
                case _ => Some(allControllers.get(0))
            }
        } catch {
            case e:Error => Debug.error(e.toString) ; None
        }
    }

    val controller:Option[JoypadWrapper] = rawController map (new JoypadWrapper(_))
    def round(axisValue:Float) = scala.math.signum((100*axisValue).toInt)
}