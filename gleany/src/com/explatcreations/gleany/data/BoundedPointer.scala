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

package com.explatcreations.gleany.data

import com.explatcreations.gleany.GleanyMath

class BoundedPointer(max: Int) extends Pointer(max) {
    protected var sign = 1

    override def increment() {
        raw = GleanyMath.clamp(raw + sign*1, 0, max - 1)
    }

    override def decrement() {
        raw = GleanyMath.clamp(raw - sign*1, 0, max - 1)
    }

    def isAtBeginning = raw <= 0

    def isAtEnd = raw >= max - 1

    def reverse() {
        sign = -sign
    }
}

class LoopedBoundedPointer(max: Int) extends BoundedPointer(max) {
    override def increment() {
        raw = (raw + sign*1 + max) % max
    }

    override def decrement() {
        raw = (raw - sign*1 + max) % max
    }
}
