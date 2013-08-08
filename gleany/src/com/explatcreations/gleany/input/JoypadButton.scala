package com.explatcreations.gleany.input

trait ButtonInfo
case class AxisButton(code:Int, value:Int) extends ButtonInfo
case class FaceButton(code:Int) extends ButtonInfo

object JoypadButton {
    def fromString(string:String):JoypadButton = new JoypadButton(string, Map(string))

    val Map = List(
        ("DPadUp", AxisButton(0, -1)),
        ("DPadDown", AxisButton(0, 1)),
        ("DPadLeft", AxisButton(1, -1)),
        ("DPadRight", AxisButton(1, 1)),
        ("1", FaceButton(0)),
        ("2",  FaceButton(1)),
        ("3",  FaceButton(2)),
        ("4",  FaceButton(3)),
        ("5",  FaceButton(4)),
        ("6",  FaceButton(5)),
        ("7",  FaceButton(6)),
        ("8",  FaceButton(7)),
        ("9",  FaceButton(8)),
        ("10",  FaceButton(9))
    ).toMap

    //todo -- make descriptive shortcuts
    val All = Map map {case (name, info) => new JoypadButton(name, info)}
}

case class JoypadButton(descriptor:String, info:ButtonInfo) {
    override def toString:String = descriptor
}
