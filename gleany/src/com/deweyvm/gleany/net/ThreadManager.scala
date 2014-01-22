package com.deweyvm.gleany.net

object ThreadManager {
  def spawn[T <: Task](task:T): T = {
    new Thread(new Runnable {
      override def run() {
        task.execute()
      }
    }).start()
    task
  }
}
