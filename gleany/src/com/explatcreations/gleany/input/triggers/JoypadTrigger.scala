package com.explatcreations.gleany.input.triggers

import com.explatcreations.gleany.input.{FaceButton, AxisButton, JoypadButton}


class JoypadTrigger(val button:JoypadButton) extends Trigger {
    import JoypadHelper._
    override def isPressed = {
        button.info match {
            case AxisButton(code, value) => controller exists { _.isAxisPressed(code) == value }
            case FaceButton(code) => controller exists { _.isButtonPressed(code) }
        }
    }
}