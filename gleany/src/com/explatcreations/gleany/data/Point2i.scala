package com.explatcreations.gleany.data

import com.explatcreations.gleany.data.Point2f


object Point2i {
    val Zero = Point2i(0,0)
}

case class Point2i(x:Int, y:Int) {
    def this() = {
        this(0,0)
    }

    override def equals(obj: Any): Boolean ={
        obj.isInstanceOf[Point2i] && obj.asInstanceOf[Point2i] == this
    }

    override def hashCode = {
        x + y >> 8
    }

    def +(other:Point2i) = Point2i(x + other.x, y + other.y)
    def -(other:Point2i) = Point2i(x - other.x, y - other.y)
    def ==(other:Point2i) = x == other.x && y == other.y
    def *:(scale:Int) = Point2i(scale*x, scale*y)
    def *(scale:Int) = Point2i(scale*x, scale*y)
    def toPoint2f = new Point2f(x, y)
    def toTuple = (x,y)

    override def toString = "(%d,%d)".format(x, y)
}
