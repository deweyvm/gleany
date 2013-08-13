package com.explatcreations.gleany.graphics

import com.explatcreations.gleany.data.{Point2i, Point2f}

trait Sprite2d {
    val width:Int
    val height:Int
    def update()
    def draw(pos:Point2f) {
        draw(pos.x, pos.y)
    }

    def draw(x:Float, y:Float) {
        draw(Point2f(x, y))
    }

    def draw(pos:Point2i) {
        draw(pos.x, pos.y)
    }

    final def toDoodad(x:Float, y:Float):Doodad = new Doodad(this, x, y)
}
