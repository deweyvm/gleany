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

package com.explatcreations.gleany.input


class AxisControl(minus:Control[Boolean], plus:Control[Boolean]) extends Control[Int] {
    override def update() {}

    private def check(func:Control[Boolean] => Boolean) = {
        if (func(minus)) {
            -1
        } else if (func(plus)) {
            1
        } else {
            0
        }
    }

    override def isPressed = check(_.isPressed)
    override def justPressed = check(_.justPressed)
    override def justReleased = check(_.justReleased)
    override def zip(start:Int, num:Int) = check(_.zip(start, num))

}
