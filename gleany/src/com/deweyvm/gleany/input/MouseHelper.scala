package com.deweyvm.gleany.input

import com.badlogic.gdx.{Gdx, InputAdapter, InputProcessor}

object MouseHelper {
  val wrapper = new MouseWrapper()
}

class MouseWrapper {
  var delta = 0
  val listener = new InputAdapter {
    override def scrolled(p1: Int): Boolean = {
      println(p1)
      delta = p1
      false
    }
  }
  Gdx.input.setInputProcessor(listener)
}
