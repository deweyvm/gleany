package com.explatcreations.gleany.loading

import com.explatcreations.gleany.input.JoypadButton

trait ControlName {
    def name:String
}

abstract class ControlNameCollection[+T <: ControlName] {
    def values:Seq[T]
    def fromString(string:String) : Option[T]
    def makeKeyboardDefault:Map[String, java.lang.Float]
    def makeJoypadDefault:Map[String, String]
}
