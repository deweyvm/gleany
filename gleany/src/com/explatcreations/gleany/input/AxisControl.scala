package com.explatcreations.gleany.input


class AxisControl(minus:Control[Boolean], plus:Control[Boolean]) extends Control[Int] {
    override def update() {}

    private def check(func:Control[Boolean] => Boolean) = {
        if (func(minus)) {
            -1
        } else if (func(plus)) {
            1
        } else {
            0
        }
    }

    override def isPressed = check(_.isPressed)
    override def justPressed = check(_.justPressed)
    override def justReleased = check(_.justReleased)
    override def zip(start:Int, num:Int) = check(_.zip(start, num))

}
