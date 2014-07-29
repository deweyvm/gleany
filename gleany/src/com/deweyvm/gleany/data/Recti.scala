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


object Recti {
  def apply(pos: Point2i, size: Point2i): Recti = Recti(pos.x, pos.y, size.x, size.y)
}

case class Recti(x: Int, y: Int, width: Int, height: Int) {

  lazy val right: Int = x + width - 1
  lazy val bottom: Int = y + height - 1
  lazy val center: Point2i = Point2i(x + width / 2, y + height / 2)

  def expand(xAmount:Int, yAmount:Int) =
    Recti(x - xAmount, y - yAmount, width + xAmount * 2, height + yAmount * 2)

  def contains(point: Point2d): Boolean =
    !(point.x < x || point.x > right || point.y < y || point.y > bottom)

  def contains(pt:(Int,Int)) = {
    !(pt._1 < x || pt._1 > right || pt._2 < y || pt._2 > bottom)
  }

  def containsRect(r:Recti) = {
    x <= r.x && y <= r.y && right >= r.right && bottom >= r.bottom
  }

  def intersects(other: Recti): Boolean = {
    val left = scala.math.max(x, other.x)
    val top = scala.math.max(y, other.y)
    val right = scala.math.min(this.right + 1, other.right + 1)
    val bottom = scala.math.min(this.bottom + 1, other.bottom + 1)
    (left < right) && (top < bottom)
  }

  def getOverlap(other:Recti):Option[Recti] = {
    if (!other.intersects(this)) return None
    val x = math.max(this.x, other.x)
    val y = math.max(this.y, other.y)
    val width = math.min(this.x + this.width, other.x + other.width) - x
    val height = math.min(this.y + this.height, other.y + other.height) - y
    Some(Recti(x, y, width, height))
  }

  def +(other: Recti): Recti =
    Recti(x + other.x, y + other.y, width + other.width, height + other.height)

  def area = width*height

  def toRectf: Rectf = Rectf(x, y, width, height)
  def toRectd: Rectd = Rectd(x, y, width, height)

  override def toString: String = "(%d,%d,%d,%d)" format(x, y, width, height)
}
