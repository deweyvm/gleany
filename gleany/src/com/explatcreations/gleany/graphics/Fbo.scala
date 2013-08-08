package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.{Mesh, GL10}
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.Texture.{TextureWrap, TextureFilter}
import com.badlogic.gdx.graphics.glutils.FrameBuffer

object Fbo {
    private var isDrawing = false

    def makeFrameBuffer(format:Format, width:Int, height:Int) = {
        val fbo = new FrameBuffer(format, width, height, true)
        fbo.getColorBufferTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest)
        fbo.getColorBufferTexture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge)
        fbo
    }
}

class Fbo(val width:Int, val height:Int) {
    import Fbo._
    private val fbo = makeFrameBuffer(Format.RGBA8888, width, height)

    def draw(func: () => Unit) {
        fbo.begin()
        if (isDrawing) {
            throw new RuntimeException("nested fbo drawing doesnt work")
        }
        isDrawing = true
        func()
        isDrawing = false
        fbo.end()
    }

    def drawSimple(shader:Shader, mesh:Mesh, func: () => Unit) {
        draw(func)

        shader.draw(() => {
            texture.bind()
            mesh.render(shader.shader, GL10.GL_TRIANGLE_FAN)
        })

    }

    def texture = fbo.getColorBufferTexture
}
