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

package com.explatcreations.gleany.input

import com.badlogic.gdx.Input

object ControlUtils {
    private def gatherKeyNames:Map[Int,String] = {
        def screamingCapsToCamelCase(str:String) = {
            val split:Array[String] = str.split("_")
            val combined:Array[String] = split map {
                segment:String =>
                    segment.length match {
                        case 1 | 0 => segment
                        case _ => segment.substring(0, 1) + segment.substring(1, segment.length).toLowerCase
                    }
            }
            combined.mkString
        }
        val cls = classOf[Input.Keys]
        val pairs = cls.getFields map {
            i => (i.getInt(null), screamingCapsToCamelCase(i.getName))
        }
        pairs.toMap.withDefault(f => f.toString)

    }

    private val keyNameMap = gatherKeyNames

    def getKeyName(code:Int) = keyNameMap(code)
}
