package com.deweyvm.gleany.net


abstract class Task {
  def execute():Unit
  final def start():Task = {
    ThreadManager.spawn(this)
    this
  }
}
