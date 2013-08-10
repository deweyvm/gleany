package com.explatcreations.gleany.data

import com.badlogic.gdx.math.MathUtils
import com.explatcreations.gleany.GleanyMath

object Point2f {
    val Zero = Point2f(0,0)
}

case class Point2f(x:Float, y:Float) {
    def normalize = {
        val mag = magnitude
        if (mag == 0) {
            this
        } else {
            Point2f(x/mag, y/mag)
        }
    }

    def magnitude = {
        scala.math.sqrt(x*x + y*y).toFloat
    }

    def magnitude2 = {
        x*x + y*y
    }

    def map(f:Float => Float) = Point2f(f(x), f(y))

    def clamp(min:Point2f, max:Point2f) = Point2f(GleanyMath.clamp(x, min.x, max.x),
                                                  GleanyMath.clamp(y, min.y, max.y))
    def %(d:Float) = Point2f(x % d, y % d)

    def -(other:Point2f) = Point2f(x - other.x, y - other.y)
    def +(other:Point2f) = Point2f(x + other.x, y + other.y)
    def *:(scale:Float) = Point2f(scale*x, scale*y)
    def *(scale:Float) = Point2f(scale*x, scale*y)
    def /(scale:Float) = Point2f(x/scale, y/scale)
    def unary_- = Point2f(-x, -y)
    def toTuple = (x, y)
    def toPoint2i = Point2i(x.toInt, y.toInt)
}
