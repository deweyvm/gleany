package com.explatcreations.gleany.data


object WrappedArray {
    def nonLooped[T](elements:IndexedSeq[T]) = {
        new WrappedArray(elements, false)
    }
}

class WrappedArray[T](seq:IndexedSeq[T], looped:Boolean) {

    val length = seq.length
    if (seq.length == 0) {
        throw new RuntimeException("WrappedArray must be nonempty")
    }

    private val ptr = {
        if (looped) {
            new LoopedBoundedPointer(seq.length)
        } else {
            new BoundedPointer(seq.length)
        }
    }

    def increment() {
        ptr.increment()
    }

    def decrement() {
        ptr.decrement()
    }

    def isAtBeginning = ptr.isAtBeginning

    def isAtEnd = ptr.isAtEnd

    def reverse() {
        ptr.reverse()
    }

    def foreach(func:T=>Unit) {
        seq foreach func
    }

    def zipWithIndex = {
        seq.zipWithIndex
    }

    def reset() {
        ptr.setToBeginning()
    }

    def get = ptr.get(seq)

    def doSelected(func:(T, Boolean) => Unit) {
        val selected = get
        foreach {t =>
            func(t, t == selected)
        }
    }

    def setToEnd() {
        ptr.setToEnd()
    }
}
