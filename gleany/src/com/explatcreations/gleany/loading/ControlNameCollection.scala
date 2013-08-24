package com.explatcreations.gleany.loading

trait ControlName {
    def name:String
}

abstract class ControlNameCollection[+T <: ControlName] {
    def values:Seq[T]
    def fromString(string:String) : Option[T]
}
