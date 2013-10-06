/******************************************************************************
 * Copyright 2013, deweyvm
 *
 * This file is part of Gleany.
 *
 * Gleany is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Gleany is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along
 * with Gleany.
 *
 * If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

package com.explatcreations.gleany

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.Gdx
import com.explatcreations.gleany.data.Recti
import com.explatcreations.gleany.audio.{Music, Sfx}

object AssetLoader {

    def loadTexture(name: String) = new Texture(Glean.y.files.texture(name))


    def makeTextureRegion(texture: Texture, recti: Option[Recti]=None) = {
        val result = recti map { r =>
            new TextureRegion(texture, r.x, r.y, r.width, r.height)
        } getOrElse new TextureRegion(texture)
        result.flip(false, true)
        result
    }

    def loadFont(name: String, pt: Int) = {
        val gen = new FreeTypeFontGenerator(Glean.y.files.font(name))
        val font = gen.generateFont(pt, FreeTypeFontGenerator.DEFAULT_CHARS, true)
        gen.dispose()
        font
    }

    /**
     * todo: could we automatically detect the extension?
     */
    def loadSound(name: String, looped: Boolean) = {
        val sound = Gdx.audio.newMusic(Glean.y.files.sfx(name))
        new Sfx(Glean.y.audio, sound, looped)
    }

    def loadMusic(name: String) = {
        val music = Gdx.audio.newMusic(Glean.y.files.music(name))
        new Music(Glean.y.audio, music)
    }

}
