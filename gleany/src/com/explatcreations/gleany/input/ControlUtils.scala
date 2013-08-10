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
