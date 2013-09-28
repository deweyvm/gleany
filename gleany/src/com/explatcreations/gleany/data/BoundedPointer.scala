package com.explatcreations.gleany.data

import com.badlogic.gdx.math.MathUtils

class BoundedPointer(max:Int) extends Pointer(max) {
    protected var sign = 1

    override def increment() {
        raw = MathUtils.clamp(raw + sign*1, 0, max - 1)
    }

    override def decrement() {
        raw = MathUtils.clamp(raw - sign*1, 0, max - 1)
    }

    def isAtBeginning = raw <= 0

    def isAtEnd = raw >= max - 1

    def reverse() {
        sign = -sign
    }
}

class LoopedBoundedPointer(max:Int) extends BoundedPointer(max) {
    override def increment() {
        raw = (raw + sign*1 + max) % max
    }

    override def decrement() {
        raw = (raw - sign*1 + max) % max
    }
}
