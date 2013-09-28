package com.explatcreations.gleany.data

abstract class Pointer(max:Int) {
    def increment()
    def decrement()

    protected var raw = 0

    def setToBeginning() {
        raw = 0
    }

    def setToEnd() {
        raw = max - 1
    }

    def get[T](seq:IndexedSeq[T]):T = seq(raw)
}
