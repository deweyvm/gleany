package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.g2d.Sprite

class Renderer {
    private val batch = new Batch
    def draw(sprite:Sprite) {
        batch.draw(sprite)
    }
    def drawBatch(drawer:() => Unit) {
        batch.draw(drawer)
    }
}
