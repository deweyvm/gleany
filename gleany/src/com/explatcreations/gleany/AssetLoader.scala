package com.explatcreations.gleany

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.Gdx
import com.explatcreations.gleany.data.Recti

object AssetLoader {

    val TexturePath = "sprites"
    val FontPath = "fonts"
    val SoundPath = "sfx"
    val MusicPath = "music"

    def loadTexture(name:String) = {
        val path = TexturePath + "/" + name + ".png"
        new Texture(path)
    }

    def makeTextureRegion(texture:Texture, recti:Option[Recti]=None) = {
        val result = recti map { r =>
            new TextureRegion(texture, r.x, r.y, r.width, r.height)
        } getOrElse new TextureRegion(texture)
        result.flip(false, true)
        result
    }


    def loadFont(name:String, pt:Int) = {
        val gen = new FreeTypeFontGenerator(Gdx.files.internal(FontPath + "/" + name))
        val font = gen.generateFont(pt, FreeTypeFontGenerator.DEFAULT_CHARS, true)
        gen.dispose()
        font
    }
}
