package com.explatcreations.gleany.input

import com.badlogic.gdx.controllers.{ControllerAdapter, Controller}
import collection.mutable
import triggers.JoypadHelper

class JoypadWrapper(controller:Controller) {
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

    def isButtonPressed(code:Int):Boolean = {
        buttonState(code)
    }

    def isAxisPressed(code:Int):Float = {
        axisState(code)
    }
}
