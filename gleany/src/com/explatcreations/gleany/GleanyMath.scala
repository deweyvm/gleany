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

package com.explatcreations.gleany

object GleanyMath {
    //this is cute but cba with overhead here, often used in tight loops
    /*def clamp[T](value: T, min: T, max: T)(implicit n: Numeric[T]) = {
        import n.{lt,gt}
        if (lt(value, min)) {
            min
        } else if (gt(value, max)) {
            max
        } else {
            value
        }
    }*/

    def clamp(value: Float, min: Float, max: Float): Float = {
        if (value < min) value
        else if (value > max) max
        else value
    }

    def clamp(value: Int, min: Int, max: Int): Int = {
        if (value < min) value
        else if (value > max) max
        else value
    }
}
