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

package com.deweyvm.gleany.data


object Point2i {
  val Zero: Point2i = Point2i(0, 0)
}

case class Point2i(x: Int, y: Int) {
  def this() = this(0, 0)

  override def equals(obj: Any): Boolean =
    obj.isInstanceOf[Point2i] && obj.asInstanceOf[Point2i] == this


  override def hashCode: Int = x + y >> 8

  def +(other: Point2i): Point2i = Point2i(x + other.x, y + other.y)

  def -(other: Point2i): Point2i = Point2i(x - other.x, y - other.y)

  def ==(other: Point2i): Boolean = x == other.x && y == other.y

  def *:(scale: Int): Point2i = Point2i(scale * x, scale * y)

  def *(scale: Int): Point2i = Point2i(scale * x, scale * y)

  def /(scale: Int): Point2i = Point2i(x/scale, y/scale)

  def toPoint2d: Point2d = new Point2d(x, y)

  def toTuple: (Int, Int) = (x, y)

  override def toString: String = "(%d,%d)".format(x, y)
}
