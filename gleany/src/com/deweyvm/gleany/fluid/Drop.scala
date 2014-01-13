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

package com.deweyvm.gleany.fluid

import util.Random

class Drop(var x: Float, var y: Float) {
  var g: Float = Random.nextFloat()
  var b: Float = Random.nextFloat()
  var density: Float = 0f
  var nearDensity: Float = 0f
  var pressure: Float = 0f
  var nearPressure: Float = 0f

  var isEjecting: Boolean = false

  var xPrev: Float = 0f
  var yPrev: Float = 0f
  var vx: Float = 0f
  var vy: Float = 0f
}
