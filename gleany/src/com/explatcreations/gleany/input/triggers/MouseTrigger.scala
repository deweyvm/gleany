package com.explatcreations.gleany.input.triggers

import com.badlogic.gdx.Gdx

class MouseTrigger extends Trigger {
    override def isPressed = {
        Gdx.input.isTouched
    }
}
