package com.deweyvm.gleany.graphics


class SpriteFactory[T <: Sprite2d](generator: () => T) {
  private val saved = getUnique
  def get: T = saved
  def getUnique: T = generator()
}
