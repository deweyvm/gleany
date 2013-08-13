package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.{Texture, Pixmap}
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.g2d.Sprite
import com.explatcreations.gleany.Singletons


object RectSprite {
    private val texture = makeTexture
    private def makeTexture = {
        val pixmap = new Pixmap(1, 1, Format.RGBA8888)
        pixmap.setColor(Color.White.toLibgdxColor)
        pixmap.fill()
        val result = new Texture(pixmap)
        pixmap.dispose()
        result
    }

    private def makeSprite(width:Int, height:Int, color:Color) = {
        val result = new Sprite(texture)
        result.setOrigin(0,0)
        result.setScale(width, height)
        result.setColor(color.toLibgdxColor)
        result
    }
}

class RectSprite(override val width:Int, override val height:Int, color:Color) extends Sprite2d {
    import RectSprite._
    private val sprite = makeSprite(width, height, color)

    override def update() {}

    def setAlpha(a:Float) = {
        sprite.setColor(color.copy(a=a).toLibgdxColor)
        this
    }

    def setSize(width:Int, height:Int) = {
        new RectSprite(width, height, color)
    }

    override def draw(x:Float, y:Float) {
        sprite.setPosition(x, y)
        Singletons.renderer.draw(sprite)
    }
}
