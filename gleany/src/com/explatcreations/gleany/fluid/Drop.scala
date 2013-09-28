package com.explatcreations.gleany.fluid

import util.Random

class Drop(var x:Float, var y:Float) {
    var g = Random.nextFloat()
    var b = Random.nextFloat()
    var density = 0f
    var nearDensity = 0f
    var pressure = 0f
    var nearPressure = 0f

    var isEjecting = false

    var xPrev = 0f
    var yPrev = 0f
    var vx = 0f
    var vy = 0f
}
