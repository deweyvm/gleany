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

package com.deweyvm.gleany.graphics

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.graphics.{Color => LibgdxColor}
import com.deweyvm.gleany.GleanyMath
import scala.util.Random

object Color {
  val Red: Color = Color(1, 0, 0)
  val Blue: Color = Color(0, 0, 1)
  val DarkBlue: Color = Color(0, 0, 0.5)
  val Cyan: Color = Color(0, 1, 1)
  val Yellow: Color = Color(1, 1, 0.01)
  val Tan: Color = Color(210/256.0, 180/256.0, 140/256.0)
  val Pink: Color = Color(1, 0.68, 0.68)
  val Brown: Color = Color(0.58, 0.29, 0)
  val GrossGreen: Color = Color(0.58/5, 0.99/5, 0)
  val Black: Color = Color(0, 0, 0)
  val Blank: Color = Color(0, 0, 0, 0)
  val Grey: Color = Color(0.7, 0.7, 0.7)
  val DarkGrey: Color = Color(0.153, 0.157, 0.133)
  val LightGreen: Color = Color(0.5, 1, 0.5)
  val Green: Color = Color(0, 1, 0)
  val DarkGreen: Color = Color(0, 0.5, 0)
  val Purple: Color = Color(202 / 256.0, 0, 140 / 256.0)
  val DarkPurple: Color = Color(4/256.0, 0, 50/256.0)
  val Teal: Color = Color(0, 0.5, 0.5)
  val White: Color = Color(1, 1, 1)
  val Violet: Color = Color(204 / 256.0, 0, 125 / 256.0)
  val Orange: Color = Color(1, 0.58, 0)

  def fromHsb(i: Double, saturation: Double = 1, brightness: Double = 1): Color = {
    val hue = GleanyMath.clamp(i, 0, 1)
    val hsv = java.awt.Color.getHSBColor(hue.toFloat, saturation.toFloat, brightness.toFloat)
    val r = hsv.getRed / 256.0
    val b = hsv.getBlue / 256.0
    val g = hsv.getGreen / 256.0
    new Color(r, g, b, 1)
  }

  def fromRgb(r: Double, g: Double, b: Double): Color = Color(r, g, b, 1)

  def fromRgba(r: Double, g: Double, b: Double, a: Double): Color = Color(r, g, b, a)

  def randomHue(): Color = fromHsb(Random.nextDouble(), 1, 1)

  def blend(c1: Color, c2: Color, prop: Double): Color = {
    val r = c1.r * prop + c2.r * (1 - prop)
    val g = c1.g * prop + c2.g * (1 - prop)
    val b = c1.b * prop + c2.b * (1 - prop)
    Color(r, g, b, 1)
  }

}

case class Color(r: Double, g: Double, b: Double, a: Double = 1) {
  private val libgdxColor = new LibgdxColor(r.toFloat, g.toFloat, b.toFloat, a.toFloat)

  def toLibgdxColor: LibgdxColor = libgdxColor
  def toByteTuple:(Int,Int,Int) = ((r*256).toInt,(g*256).toInt,(b*256).toInt)

  def dim(factor: Double): Color = Color(r / factor, g / factor, b / factor)
  def mix(other:Color, amt:Double) =
    Color(r*(1-amt) + other.r*amt,
          g*(1-amt) + other.g*amt,
          b*(1-amt) + other.b*amt
    )
  def brighten(factor: Double): Color = Color(r+factor, g+factor, b+factor)
}
