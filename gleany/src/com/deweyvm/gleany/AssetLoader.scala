/** ****************************************************************************
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
  * ****************************************************************************/

package com.deweyvm.gleany

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureRegion}
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.Gdx
import com.deweyvm.gleany.data.Recti
import com.deweyvm.gleany.audio.{Music, Sfx}
import com.deweyvm.gleany.loading.GleanyTiledMap

object AssetLoader {
  def loadTexture(name: String): Texture = new Texture(Glean.y.files.texture(name))

  def makeTextureRegion(texture: Texture, recti: Option[Recti] = None): TextureRegion = {
    val result = recti map { r =>
      new TextureRegion(texture, r.x, r.y, r.width, r.height)
    } getOrElse new TextureRegion(texture)
    result.flip(false, true)
    result
  }

  def loadFont(name: String, pt: Int): BitmapFont = {
    val gen = new FreeTypeFontGenerator(Glean.y.files.font(name))
    val font = gen.generateFont(pt, FreeTypeFontGenerator.DEFAULT_CHARS, true)
    gen.dispose()
    font
  }

  def loadSound(name: String, looped: Boolean): Sfx = {
    val sound = Gdx.audio.newMusic(Glean.y.files.sfx(name))
    new Sfx(Glean.y.audio, sound, looped)
  }

  def loadMusic(name: String): Music = {
    val music = Gdx.audio.newMusic(Glean.y.files.music(name))
    new Music(Glean.y.audio, music)
  }

  def loadTmx(name: String): GleanyTiledMap = {
    new GleanyTiledMap(name)
  }

}
