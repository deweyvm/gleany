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

package com.deweyvm.gleany.data

import com.deweyvm.gleany.GleanyMath
import com.badlogic.gdx.math.MathUtils

object Point2d {
  val Zero: Point2d = Point2d(0, 0)
  val UnitX: Point2d = Point2d(1, 0)
}

case class Point2d(x: Double, y: Double) {
  def normalize: Point2d = {
    val mag = magnitude
    if (mag == 0) {
      this
    } else {
      Point2d(x / mag, y / mag)
    }
  }

  def angle: Double = MathUtils.atan2(y.toFloat, x.toFloat)

  def magnitude: Double = math.sqrt(x * x + y * y)

  def magnitude2: Double = x * x + y * y

  def dot(other:Point2d) = other.x*x + other.y*y

  def map(f: Double => Double): Point2d = Point2d(f(x), f(y))

  def clamp(min: Point2d, max: Point2d):Point2d =
    Point2d(GleanyMath.clamp(x, min.x, max.x), GleanyMath.clamp(y, min.y, max.y))

  def %(d: Double): Point2d = Point2d(x % d, y % d)

  def -(other: Point2d): Point2d = Point2d(x - other.x, y - other.y)

  def +(other: Point2d): Point2d = Point2d(x + other.x, y + other.y)

  def *:(scale: Double): Point2d = Point2d(scale * x, scale * y)

  def *(scale: Double): Point2d = Point2d(scale * x, scale * y)

  def /(scale: Double): Point2d = Point2d(x / scale, y / scale)

  def rotate(angle: Double): Point2d = {
    val sin = math.sin(angle)
    val cos = math.cos(angle)
    new Point2d(x * cos - y * sin, x * sin + y * cos)
  }

  def unary_- : Point2d = Point2d(-x, -y)

  def toTuple: (Double, Double) = (x, y)

  def toPoint2i: Point2i = Point2i(x.toInt, y.toInt)

  def to3 = Point3d(x, y, 0)

  override def toString = "Point2d(%.2f,%.2f)" format (x, y)
}
