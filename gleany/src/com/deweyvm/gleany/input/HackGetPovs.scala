package com.badlogic.gdx.controllers.desktop

import com.badlogic.gdx.controllers.{Controller, ControlType}
import com.deweyvm.gleany.Debug

object HackGetPovs {
  def getPovCount(controller: Controller): Int = {
    try {
      val method = controller.getClass.getMethod("getControlCount", classOf[ControlType])
      val numPovs = method.invoke(controller, ControlType.pov).asInstanceOf[Int]
      numPovs
    } catch {
      case nsm:Throwable =>
        Debug.debug(nsm.getMessage)
        0
    }
  }
}
