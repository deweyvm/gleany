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


case class Rectf(x: Float, y: Float, width: Float, height: Float) {
  lazy val right: Float = x + width - 1
  lazy val bottom: Float = y + height - 1
  lazy val center: Point2f = Point2f(x + width / 2, y + height / 2)

  def intersects(other: Rectf): Boolean = {
    val left = scala.math.max(x, other.x)
    val top = scala.math.max(y, other.y)
    val right = scala.math.min(x + width, other.x + other.width)
    val bottom = scala.math.min(y + height, other.y + other.height)
    (left < right) && (top < bottom)
  }

  def intersects(other: Recti): Boolean = intersects(other.toRectf)

  def +(other: Rectf): Rectf = Rectf(x + other.x, y + other.y, width + other.width, height + other.height)


}
