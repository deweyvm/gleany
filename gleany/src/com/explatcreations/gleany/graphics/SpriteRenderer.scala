package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import scala.collection.mutable.ArrayBuffer

class SpriteRenderer {
    val batch = new SpriteBatch(10000)

    var draws:ArrayBuffer[() => Unit] = ArrayBuffer[() => Unit]()

    def addCall(call:() => Unit) {
        draws += call
    }

    def draw(sprite:Sprite) {
        addCall (() => sprite.draw(batch))
    }

    /*def draw(fontCache:HackFontCache, start:Int, end:Int) {
        addCall(() => fontCache.draw(batch, start, end))
    }

    def draw(fontCache:HackFontCache) {
        addCall(() => fontCache.draw(batch))
    }*/
}
