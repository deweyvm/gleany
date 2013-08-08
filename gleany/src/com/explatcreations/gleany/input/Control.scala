package com.explatcreations.gleany.input

trait Control[+T <: AnyVal] {
    def update()
    def isPressed:T
    def justPressed:T
    def justReleased:T
    def zip(start:Int, num:Int):T
}
