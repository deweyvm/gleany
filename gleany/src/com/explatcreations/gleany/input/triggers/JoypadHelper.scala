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