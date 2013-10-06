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

package com.explatcreations.gleany.input

trait ButtonSpec

case class AxisButton(code: Int, value: Int) extends ButtonSpec

case class FaceButton(code: Int) extends ButtonSpec

object JoypadButton {
  def fromString(string: String): JoypadButton = new JoypadButton(string, Map(string))

  val Map = List(
    ("DPadUp", AxisButton(0, -1)),
    ("DPadDown", AxisButton(0, 1)),
    ("DPadLeft", AxisButton(1, -1)),
    ("DPadRight", AxisButton(1, 1)),
    ("1", FaceButton(0)),
    ("2", FaceButton(1)),
    ("3", FaceButton(2)),
    ("4", FaceButton(3)),
    ("5", FaceButton(4)),
    ("6", FaceButton(5)),
    ("7", FaceButton(6)),
    ("8", FaceButton(7)),
    ("9", FaceButton(8)),
    ("10", FaceButton(9))
  ).toMap

  //todo -- make descriptive shortcuts
  val All = Map map {
    case (name, info) => new JoypadButton(name, info)
  }
}

case class JoypadButton(descriptor: String, info: ButtonSpec) {
  override def toString: String = descriptor
}
