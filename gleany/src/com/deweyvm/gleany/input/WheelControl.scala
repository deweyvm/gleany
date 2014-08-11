package com.deweyvm.gleany.input

/**
 * Requires that update is called after all querying in a frame
 */
class WheelControl extends Control[Int] {
  def update() {
    MouseHelper.wrapper.delta = 0
  }
  def isPressed = justPressed
  def justPressed = MouseHelper.wrapper.delta
  def justReleased = 0
  def zip(start: Int, num: Int) = 0
}
