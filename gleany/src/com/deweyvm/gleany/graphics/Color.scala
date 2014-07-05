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

object Color {
  val Red: Color = Color(1, 0, 0)
  val Blue: Color = Color(0, 0, 1)
  val DarkBlue: Color = Color(0, 0, 0.5f)
  val Cyan: Color = Color(0, 1, 1)
  val Yellow: Color = Color(1, 1, 0.01f)
  val Tan: Color = Color(210/256f, 180/256f, 140/256f)
  val Pink: Color = Color(1, 0.68f, 0.68f)
  val Brown: Color = Color(0.58f, 0.29f, 0f)
  val GrossGreen: Color = Color(0.58f/5, 0.99f/5, 0f)
  val Black: Color = Color(0, 0, 0)
  val Blank: Color = Color(0, 0, 0, 0)
  val Grey: Color = Color(0.7f, 0.7f, 0.7f)
  val DarkGrey: Color = Color(0.153f, 0.157f, 0.133f)
  val LightGreen: Color = Color(0.5f, 1f, 0.5f)
  val Green: Color = Color(0, 1, 0)
  val DarkGreen: Color = Color(0, 0.5f, 0)
  val Purple: Color = Color(202 / 256f, 0, 140 / 256f)
  val DarkPurple: Color = Color(4/256f, 0, 50/256f)
  val Teal: Color = Color(0, 0.5f, 0.5f)
  val White: Color = Color(1, 1, 1)
  val Violet: Color = Color(204 / 255f, 0, 125 / 255f)
  val Orange: Color = Color(1, 0.58f, 0)

  def fromHsb(i: Float, saturation: Float = 1f, brightness: Float = 1f): Color = {
    val hue = GleanyMath.clamp(i, 0, 1)
    val hsv = java.awt.Color.getHSBColor(hue, saturation, brightness)
    val r = hsv.getRed / 255f
    val b = hsv.getBlue / 255f
    val g = hsv.getGreen / 255f
    new Color(r, g, b, 1)
  }

  def fromRgb(r: Float, g: Float, b: Float): Color = Color(r, g, b, 1)

  def fromRgba(r: Float, g: Float, b: Float, a: Float): Color = Color(r, g, b, a)

  def randomHue(): Color = fromHsb(MathUtils.random(), 1, 1)

  def blend(c1: Color, c2: Color, prop: Float): Color = {
    val r = c1.r * prop + c2.r * (1 - prop)
    val g = c1.g * prop + c2.g * (1 - prop)
    val b = c1.b * prop + c2.b * (1 - prop)
    Color(r, g, b, 1)
  }

}

case class Color(r: Float, g: Float, b: Float, a: Float = 1) {
  private val libgdxColor = new LibgdxColor(r, g, b, a)

  def toLibgdxColor: LibgdxColor = libgdxColor
  def toByteTuple:(Int,Int,Int) = ((r*255).toInt,(g*255).toInt,(b*255).toInt)

  def dim(factor: Float): Color = Color(r / factor, g / factor, b / factor)
  def brighten(factor: Float): Color = Color(r+factor, g+factor, b+factor)
}
