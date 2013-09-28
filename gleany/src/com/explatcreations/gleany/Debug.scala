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

package com.explatcreations.gleany

object Debug {
    val debugLoadingSequence = false
    def debug(string:String, stackOffset:Int=2) {
        val callStack = Thread.currentThread().getStackTrace
        val className = callStack(stackOffset).getClassName.split("""[.]""").last.replace("$", "")
        println("%s: %s".format(className, string))
    }

    def load() {
        if (debugLoadingSequence) {
            debug("loaded", 3)
        }
    }

    def error(string:String) {
        System.err.println(string)
    }
}