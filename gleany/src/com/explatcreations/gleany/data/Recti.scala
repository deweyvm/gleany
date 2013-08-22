package com.explatcreations.gleany.data


object Recti {
    def apply(pos:Point2i, size:Point2i):Recti = {
        Recti(pos.x, pos.y, size.x, size.y)
    }
}

case class Recti(x:Int, y:Int, width:Int, height:Int) {



    lazy val right = x + width - 1
    lazy val bottom = y + height - 1

    lazy val center = Point2i(x + width/2, y + height/2)

    def contains(point:Point2f) = {
        !(point.x < x || point.x > right || point.y < y || point.y > bottom)
    }

    def intersects(other:Recti) = {
        val left = scala.math.max(x, other.x)
        val top = scala.math.max(y, other.y)
        val right = scala.math.min(this.right + 1, other.right + 1)
        val bottom = scala.math.min(this.bottom + 1, other.bottom + 1)
        (left < right) && (top < bottom)
    }

    def +(other:Recti) = Recti(x + other.x, y + other.y, width + other.width, height + other.height)

    def toRectf = Rectf(x, y, width, height)
    override def toString = "(%d,%d,%d,%d)" format (x, y, width, height)
}
