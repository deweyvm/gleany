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
import scala.collection.immutable.IndexedSeq

class GleanyTiledMap(name:String) {
    val xml = new XmlReader()
    val root = xml.parse(Glean.y.files.map(name))
    val gidMap = makeGidMap
    val layers = makeLayers
    //val objects = makeObjects
    val properties = makeProperties
    def makeGidMap:Map[String, Int] = {
        val elements = root.getChildrenByName("tileset")
        val pairs = elements.toArray map { e:XmlReader.Element =>
            val name = e.get("name")
            val firstGid = e.getInt("firstgid")
            (name, firstGid)
        }
        pairs.toMap
    }

    def makeLayers:Map[String,Array[Array[Int]]] = {
        val elements = root.getChildrenByName("layer")
        val pairs = elements.toArray map { e:XmlReader.Element =>
            val name = e.get("name")
            val width = e.getInt("width")
            val height = e.getInt("height")
            println(name + " " + width + " " + height)
            val tiles: Array[Array[Int]] = parseCsv(gidMap(name), e.getChildByName("data").getText, width, height)
            (name, tiles)
        }
        pairs.toMap
    }

    def makeProperties = {
        val propertyElement = root.getChildByName("properties")
        val pairs = 0 until propertyElement.getChildCount map { j:Int =>
            val prop = propertyElement.getChild(j)
            val key = prop.get("name")
            val value = prop.get("value")
            (key, value)
        }
        pairs.toMap
    }

    def parseCsv(firstGid:Int, data:String, width:Int, height:Int):Array[Array[Int]] = {
        val rows = data.split("\r\n|\r|\n")
        rows.map {str => str.replaceAll(",\\s*$", "").split(",\\s*").map(_.toInt - firstGid)}
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
