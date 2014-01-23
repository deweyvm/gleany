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

import java.lang.String
import scala.Predef.String
import java.text.SimpleDateFormat
import java.util.Date
import java.io.{PrintStream, File}

object Logger {
  def attachCrasher(isDebug: Boolean, root:String=".") {
    if (isDebug) {
      return
    }
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler {
      def uncaughtException(t: Thread, e: Throwable) {
        try {
          val dateString: String = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date)
          val filename: String = String.format("crash_log_%s.log", dateString)
          val file: File = new File(root + "/" + filename)
          file.createNewFile
          val stream: PrintStream = new PrintStream(file)
          e.printStackTrace(stream)
          stream.close()
        } catch {
          case throwable: Throwable => {
            e.printStackTrace()
            throwable.printStackTrace()
          }
        }
      }
    })
  }
}


