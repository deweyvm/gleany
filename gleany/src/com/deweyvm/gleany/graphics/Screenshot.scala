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

package com.deweyvm.gleany.graphics

import java.io.{File, FileOutputStream}
import com.deweyvm.gleany.utils.PNG
import com.badlogic.gdx.graphics.{GL10, Pixmap}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap.Format

object Screenshot {
  def saveFrameBuffer(fbo:Fbo, filename:String) {
    fbo.draw(() => {
      val width = fbo.width
      val height = fbo.height
      Gdx.gl.glPixelStorei(GL10.GL_PACK_ALIGNMENT, 1)

      val pixmap = new Pixmap(width, height, Format.RGBA8888)
      val pixels = pixmap.getPixels
      Gdx.gl.glReadPixels(0, 0, width, height, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixels)
      savePixmap(pixmap, filename, width, height)
      pixmap.dispose()
    })

  }

  private def savePixmap(pixmap:Pixmap, filename:String, width:Int, height:Int) {
    val bytes = PNG.write(pixmap)
    val file = new File(filename)
    val stream = new FileOutputStream(file)
    stream.write(bytes)
    stream.close()
  }
}
