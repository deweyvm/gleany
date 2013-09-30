/******************************************************************************
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
 *****************************************************************************/

package com.explatcreations.gleany.logging


import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL10
object HardwareInfo
class HardwareInfo {
    val os = getOS

    def getOS: OS = {
        val OSName= System.getProperty("os.name").toLowerCase
        if (OSName.contains("win")) {
            OS.Windows
        } else if (OSName.contains("mac")) {
            OS.Mac
        } else {
            OS.Linux
        }
    }

    private def getGpuInfo: String = {
        val gpu: String = Gdx.gl.glGetString(GL10.GL_RENDERER)
        val glVersion: String = Gdx.gl.glGetString(GL10.GL_VERSION)
        gpu + ", GL version " + glVersion
    }

    def getOutput(command: String*): String = {
        try {
            import scala.sys.process._
            val seq:Seq[String] = command
            val result = seq.lines.fold("")((a:String, b:String) => a + b)
            result
        } catch {
            case t : Throwable => {
                t.printStackTrace()
                "Failure "
            }
        }
    }

    private def getProcessorName = {
        if (os == OS.Windows) {
            System.getenv("PROCESSOR_IDENTIFIER")
        } else {
            val lines = Gdx.files.absolute("/proc/cpuinfo").readString().split("\n")
            lines.find(_.contains("model name")) map {
                _.replaceAll("model name\\s*:[ ]*", "")
            } getOrElse "Error"
        }
    }

    private def getNumProcessors: String = {
        if (os eq OS.Windows) {
            System.getenv("NUMBER_OF_PROCESSORS")
        } else {
            val lines = Gdx.files.absolute("/proc/cpuinfo").readString().split("\n")
            lines.filter(_.contains("processor")).length.toString
        }
    }

    override def toString: String = {
        val numProcessors: String = getNumProcessors
        val processorName: String = getProcessorName
        val os: String = System.getProperty("os.name")
        val gpu: String = getGpuInfo
        """|---- System information ----
           |Operating system     : %1$s
           |Number of processors : %2$s
           |Processor name       : %3$s
           |Graphics device      : %4$s
        """.stripMargin.format(os, numProcessors, processorName, gpu)
    }
}
