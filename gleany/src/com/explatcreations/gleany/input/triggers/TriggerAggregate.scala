package com.explatcreations.gleany.input.triggers

import com.badlogic.gdx.math.MathUtils
import com.explatcreations.gleany.input.Control

class TriggerAggregate(triggers:Seq[Trigger]) extends Control[Boolean] {
    private var prevFlag = 0
    private var flag = 0

    override def update() {
        prevFlag = flag
        if (triggers.exists(_.isPressed)) {
            flag = MathUtils.clamp(flag + 1, 0, Int.MaxValue - 1)
        } else {
            flag = 0
        }
    }

    override def isPressed = flag > 0
    override def justPressed = flag == 1
    override def justReleased = prevFlag != 0 && flag == 0
    override def zip(start:Int, num:Int) = justPressed || (flag > start && flag % num == 0)
}
