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

package com.explatcreations.gleany.fluid

import util.Random

class Drop(var x: Float, var y: Float) {
    var g = Random.nextFloat()
    var b = Random.nextFloat()
    var density = 0f
    var nearDensity = 0f
    var pressure = 0f
    var nearPressure = 0f

    var isEjecting = false

    var xPrev = 0f
    var yPrev = 0f
    var vx = 0f
    var vy = 0f
}
