package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}

class Batch {
    private val batch = new SpriteBatch()
    def draw(drawer:() => Unit) {
        batch.begin()
        drawer()
        batch.end()
    }

    def draw(sprite:Sprite) {
        sprite.draw(batch)
    }


}
