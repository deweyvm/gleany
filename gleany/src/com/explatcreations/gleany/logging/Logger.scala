package com.explatcreations.gleany.logging

import java.lang.String
import scala.Predef.String
import java.text.SimpleDateFormat
import java.util.Date
import java.io.{PrintStream, File}

object Logger {
    def attachCrasher(isDebug: Boolean) {
        if (isDebug) {
            return
        }
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler {
            def uncaughtException(t : Thread, e: Throwable) {
                try {
                    val dateString: String = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date)
                    val filename: String = String.format("crash_log_%s.log", dateString)
                    val file: File = new File(filename)
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


