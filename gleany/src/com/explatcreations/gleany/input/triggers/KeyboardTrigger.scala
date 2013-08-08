package com.explatcreations.gleany.input.triggers

import com.badlogic.gdx.Gdx

class KeyboardTrigger(val key:Int) extends Trigger {
    override def isPressed = {
        Gdx.input.isKeyPressed(key)
    }
}