package com.explatcreations.gleany.graphics

import com.explatcreations.gleany.data.Point2f

class Doodad(sprite:Sprite2d, x:Float, y:Float) {
    def this(sprite:Sprite2d, pos:Point2f) = {
        this(sprite, pos.x, pos.y)
    }

    def update() {
        sprite.update()
    }

    def draw() {
        sprite.draw(x, y)
    }
}
