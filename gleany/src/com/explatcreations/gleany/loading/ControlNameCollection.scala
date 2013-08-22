package com.explatcreations.gleany.loading

trait ControlName {
    def name:String
}

trait ControlNameCollection[T <: ControlName] {
    def values:Seq[T]
    def fromString(string:String) : Option[T]
}
