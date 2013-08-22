package com.explatcreations.gleany.data



case class Rectf(x:Float, y:Float, width:Float, height:Float) {
    lazy val right = x + width - 1
    lazy val bottom = y + height - 1
    lazy val center = Point2f(x + width/2, y + height/2)
    def intersects(other:Rectf) = {
        val left = scala.math.max(x, other.x)
        val top = scala.math.max(y, other.y)
        val right = scala.math.min(x + width, other.x + other.width)
        val bottom = scala.math.min(y + height, other.y + other.height)
        (left < right) && (top < bottom)
    }

    def intersects(other:Recti):Boolean = {
        intersects(other.toRectf)
    }

    def +(other:Rectf) = Rectf(x + other.x, y + other.y, width + other.width, height + other.height)


}
