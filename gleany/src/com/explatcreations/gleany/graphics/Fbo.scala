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

package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.{Texture, Mesh, GL10}
import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.Texture.{TextureWrap, TextureFilter}
import com.badlogic.gdx.graphics.glutils.FrameBuffer

object Fbo {
  private var isDrawing = false

  def makeFrameBuffer(format: Format, width: Int, height: Int): FrameBuffer = {
    val fbo = new FrameBuffer(format, width, height, true)
    fbo.getColorBufferTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest)
    fbo.getColorBufferTexture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge)
    fbo
  }
}

class Fbo(val width: Int, val height: Int) {
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

  def drawSimple(shader: Shader, mesh: Mesh, func: () => Unit) {
    draw(func)

    shader.draw(mesh, () => {
      texture.bind()
    })

  }

  def texture: Texture = fbo.getColorBufferTexture
}
