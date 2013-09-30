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

package com.explatcreations.gleany.loading

import com.badlogic.gdx.utils.XmlReader
import com.explatcreations.gleany.Glean

class GleanyTiledMap(name:String) {
    val xml = new XmlReader()
    val root = xml.parse(Glean.y.files.map(name))
    val gidMap = {
        root.getChildByName("tileset")
    }
    for (i <- 0 until root.getChildCount) {
        val element = root.getChild(i)
        if (element.getName == "properties") {
            val pairs = 0 until element.getChildCount map { j:Int =>
                val prop = element.getChild(j)
                val key = prop.get("name")
                val value = prop.get("value")
                (key, value)
            }
            pairs.toMap
        } else if (element.getName == "tileset") {
            val firstGid = element.get("firstgid")
        } else if (element.getName == "layer") {
            val name = element.get("name")
            val width = element.getInt("width")
            val height = element.getInt("height")
            println(name + " " + width + " " + height)
            0 until element.getChildCount map { j:Int =>
                val child = element.getChild(j)
                parseCsv(child.getText, width, height)
            }
        }
    }

    def parseCsv(data:String, width:Int, height:Int) {
        println(data)
        println("vs")
        val what = data.split("\r\n|\r|\n").map {str => str.replaceAll(",\\s*$", "").split(",\\s*").map(_.toInt)}
        for (j <- 0 until height) {
            for (i <- 0 until width) {
                print(what(j)(i) + ",")
            }
            println()
        }
        println()
    }
}

class IllFormedMapException(message:String) extends RuntimeException

case class MapObject(`type`:String, x:Int, y:Int, width:Int, height:Int, properties:Map[String,String])



trait ITiledMap {
    val width:Int
    val height:Int
    def getProperty(name:String):String
    def getObjectLayer(name:String):Seq[MapObject]
    def getTileLayer(name:String):Array[Array[Int]]

}
