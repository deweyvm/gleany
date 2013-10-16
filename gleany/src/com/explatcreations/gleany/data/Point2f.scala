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

package com.explatcreations.gleany.data

import com.explatcreations.gleany.GleanyMath

object Point2f {
  val Zero: Point2f = Point2f(0, 0)
  val UnitX: Point2f = Point2f(1, 0)
}

case class Point2f(x: Float, y: Float) {
  def normalize: Point2f = {
    val mag = magnitude
    if (mag == 0) {
      this
    } else {
      Point2f(x / mag, y / mag)
    }
  }

  def magnitude: Float = scala.math.sqrt(x * x + y * y).toFloat

  def magnitude2: Float = x * x + y * y

  def map(f: Float => Float): Point2f = Point2f(f(x), f(y))

  def clamp(min: Point2f, max: Point2f):Point2f =
    Point2f(GleanyMath.clamp(x, min.x, max.x), GleanyMath.clamp(y, min.y, max.y))

  def %(d: Float): Point2f = Point2f(x % d, y % d)

  def -(other: Point2f): Point2f = Point2f(x - other.x, y - other.y)

  def +(other: Point2f): Point2f = Point2f(x + other.x, y + other.y)

  def *:(scale: Float): Point2f = Point2f(scale * x, scale * y)

  def *(scale: Float): Point2f = Point2f(scale * x, scale * y)

  def /(scale: Float): Point2f = Point2f(x / scale, y / scale)

  def rotate(angle: Float): Point2f = {
    val sin = scala.math.sin(angle).asInstanceOf[Float]
    val cos = scala.math.cos(angle).asInstanceOf[Float]
    new Point2f(x * cos - y * sin, x * sin + y * cos)
  }

  def unary_- : Point2f = Point2f(-x, -y)

  def toTuple: (Float, Float) = (x, y)

  def toPoint2i: Point2i = Point2i(x.toInt, y.toInt)
}
