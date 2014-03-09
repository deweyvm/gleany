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

import com.badlogic.gdx.graphics.{Texture, Pixmap}
import com.deweyvm.gleany.utils.PNG
import java.io.{FileOutputStream, File}

object ImageUtils {
  def rangeToGreyscale(r:Double):Color = {
    val c = ((r + 1.0)/2.0 - 0.2).toFloat
    new Color(c, c, c, 1)
  }

  private def savePixmap(pixmap:Pixmap, filename:String, width:Int, height:Int) {
    val bytes = PNG.write(pixmap)
    val file = new File(filename)
    val stream = new FileOutputStream(file)
    stream.write(bytes)
    stream.close()
  }

  def printColor[T, K](a:Vector[K], f:K => Color, cols:Int, rows:Int, process:Pixmap => T):T =  {
    val pixmap = new Pixmap(cols, rows, Pixmap.Format.RGBA8888)
    for (i <- 0 until a.size) {
      val x = i % cols
      val y = i / cols
      val color = f(a(i))

      pixmap.setColor(color.toLibgdxColor)
      pixmap.drawPixel(x, y)
    }
    val result = process(pixmap)
    pixmap.dispose()
    result
  }

  def makeGreyscaleTexture(a:Vector[Double], cols:Int, rows:Int) =
    printColor(a, rangeToGreyscale, cols, rows, (p:Pixmap) => new Texture(p))

  def makeColorTexture(a:Vector[Color], cols:Int, rows:Int) =
    printColor[Texture, Color](a, x => x, cols, rows, (p:Pixmap) => new Texture(p))

}
