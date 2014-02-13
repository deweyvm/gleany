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
  private def rangeToGreyscale(r:Double) = (r + 1.0)/2.0// - 0.2

  private def savePixmap(pixmap:Pixmap, filename:String, width:Int, height:Int) {
    val bytes = PNG.write(pixmap)
    val file = new File(filename)
    val stream = new FileOutputStream(file)
    stream.write(bytes)
    stream.close()
  }

  def saveGrayscale(a:Vector[Double], cols:Int, rows:Int, filename:String) {
    val pixmap = new Pixmap(cols, rows, Pixmap.Format.RGBA8888)
    for (i <- 0 until a.size) {
      val x = i % cols
      val y = i / cols
      val c = rangeToGreyscale(a(i)).toFloat
      val color = new Color(c, c, c, 1)
      pixmap.setColor(color.toLibgdxColor)
      pixmap.drawPixel(x, y)
    }
    savePixmap(pixmap, filename, cols, rows)
    pixmap.dispose()
  }

  def makeTexture(a:Vector[Double], cols:Int, rows:Int) =
    makePixmap[Texture](a, cols, rows, (p:Pixmap) => new Texture(p))

  def makePixmap[T](a:Vector[Double], cols:Int, rows:Int, f:Pixmap => T):T = {
    val pixmap = new Pixmap(cols, rows, Pixmap.Format.RGBA8888)
    for (i <- 0 until a.size) {
      val x = i % cols
      val y = i / cols
      val c = a(i).toFloat

      val region = (c*10).toInt
      val color = if (region <= 0) {
        Color.Blue.dim(1/(1 - scala.math.abs(region - 2)/10f))
      } else if (region == 1) {
        Color.Yellow
      } else if (region == 2) {
        Color.Green
      } else if (region <= 5) {
        Color.DarkGreen
      } else if (region == 6) {
        Color.DarkGrey
      } else if (region == 7) {
        Color.Grey
      } else if (region > 7){
        Color.White
      } else {
        throw new Exception("impossible " + region)
      }
      pixmap.setColor(color.toLibgdxColor)
      pixmap.drawPixel(x, y)
    }
    val result = f(pixmap)
    pixmap.dispose()
    result
  }

  def saveHeight(a:Vector[Double], cols:Int, rows:Int, filename:String) =
    makePixmap(a, cols, rows, (p:Pixmap) => savePixmap(p, filename, cols, rows))
}
