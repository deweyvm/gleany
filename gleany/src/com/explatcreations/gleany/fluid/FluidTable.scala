/******************************************************************************
 * Copyright 2013, deweyvm
 *
 * This file is part of Gleany.
 *
 * Gleany is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Gleany is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gleany.
 * If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

package com.explatcreations.gleany.fluid

import collection.mutable.ArrayBuffer

class FluidTable(val cols:Int, val rows:Int, maxAdjacent:Int) {
    val particles = new ArrayBuffer[Drop](rows*cols)
    var grid = resetGrid
    val emptyList = ArrayBuffer[Drop]()


    def resetGrid = {
        Array.tabulate(rows*cols) { i =>
            new ArrayBuffer[Drop](maxAdjacent)
        }
    }

    def +=(drop:Drop) {
        particles += drop
    }

    def -=(drop:Drop) {
        particles -= drop
    }

    def length = particles.length

    def clear() {
        grid = resetGrid
    }

    def foreach(func:Drop => Unit) {
        particles foreach func
    }

    def getAdjacent(drop:Drop):IndexedSeq[Drop] = {
        val x = drop.x.toInt
        val y = drop.y.toInt
        if (!inRange(x, y)) {
            return emptyList
        }
        grid(x*rows + y)
    }

    def updateGrid(drop:Drop) {
        val x = drop.x.toInt
        val y = drop.y.toInt
        val xPrev = drop.xPrev.toInt
        val yPrev = drop.xPrev.toInt
        if (inRange(xPrev, yPrev)) {
            grid(xPrev*rows + yPrev) -= drop
        }
        if (inRange(x, y)) {
            grid(x*rows + y) += drop
        }
    }

    def rehash() {
        grid foreach {_.clear()}
        particles foreach  {addInterRadius(_)}
    }

    def addInterRadius(drop:Drop) {
        val posX = drop.x.toInt
        val posY = drop.y.toInt

        for (i <- -1 until 2; j <- -1 until 2) {
            val x = posX + i
            val y = posY + j
            if (inRange(x, y)) {
                grid(x*rows + y) += drop
            }
        }
    }

    def inRange(x:Float, y:Float) = {
        (x > 0 && x < cols && y > 0 && y < rows)
    }


}
