package com.explatcreations.gleany.graphics


class SpriteFactory[T <: Sprite2d](generator: () => T) {
  private val saved = getUnique
  def get: T = saved
  def getUnique: T = generator()
}
