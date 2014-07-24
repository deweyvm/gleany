/** ****************************************************************************
  * Copyright 2013, deweyvm
  *
  * This file is part of Gleany.
  *
  * Gleany is free software: you can redistribute it and/or modify it under
  * the terms of the GNU General Public License as published by the Free
  * Software Foundation, either version 3 of the License, or (at your option)
  * any later version.
  *
  * Gleany is distributed in the hope that it will be useful, but WITHOUT ANY
  * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
  * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
  * details.
  *
  * You should have received a copy of the GNU General Public License along
  * with Gleany.
  *
  * If not, see <http://www.gnu.org/licenses/>.
  * ****************************************************************************/

package com.deweyvm.gleany.logging


import java.io.{PrintStream, File}
import com.deweyvm.gleany.data.Time

object Logger {
  def attachCrasher(root:String) {
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler {
      def uncaughtException(t: Thread, e: Throwable) {
        writeCrashToFile(root, e)
        //let the program crash anyway
        System.err.print("Exception in thread \"" + t.getName + "\" ")
        e.printStackTrace(System.err)
      }
    })
  }

  def writeCrashToFile(root:String, e:Throwable) {
    try {
      val filename: String = String.format("crash_%s", Time.getString)
      val file: File = new File(root + "/" + filename)
      file.createNewFile
      val stream: PrintStream = new PrintStream(file)
      e.printStackTrace(stream)
      stream.close()
    } catch {
      case t: Exception => {
        System.err.print("Exception in thread \"" + Thread.currentThread.getName + "\" ")
        t.printStackTrace()
      }
    }
  }
}


