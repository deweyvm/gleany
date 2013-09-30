/******************************************************************************
 * Copyright 2013, deweyvm
 *
 * This file is part of Gleany.
 *
 * Gleany is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Gleany is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gleany.
 * If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

package com.explatcreations.gleany.saving

import com.badlogic.gdx.utils.Json
import java.io.{UnsupportedEncodingException, PrintWriter, File}
import java.util.Scanner
import com.explatcreations.gleany.{Gleany, Debug}
import scala.Predef.String
import java.net.URLDecoder
import scala.RuntimeException

object LoadUtils {
    private def getJarPath: String = {
        if (true/*fixme Glean.y.globals.IsDebugMode*/) {
            return "."
        }
        def getPath = {
            val path = Predef.classOf[Gleany].getProtectionDomain.getCodeSource.getLocation.getPath
            if (!new File(path).isDirectory) {
                System.getProperty("user.dir")
            } else {
                path
            }
        }
        try {
            URLDecoder.decode(getPath, "UTF-8")
        } catch {
            case uee: UnsupportedEncodingException => {
                throw new RuntimeException(uee)
            }
        }
    }

    val Directory: String = getJarPath
    val json: Json = new Json

    def loadFile(path: String): String = {
        val file: File = new File(path)
        val scanner: Scanner = new Scanner(file)
        scanner.useDelimiter("\\Z")
        scanner.next
    }

    def load[T](cl:Class[_], name:String, makeNew:() => T, verify:T => T):T = {
        def tryLoad() = {
            try {
                ensureDirectory(Directory)
                val path: String = Directory + "/" + name
                json.fromJson(cl, loadFile(path)).asInstanceOf[T]
            } catch {
                case e: Throwable => {
                    Debug.error(e.getMessage)
                    val result = makeNew()
                    flush(result, name)
                    result
                }
            }
        }
        verify(tryLoad())
    }

    def flush[T](thing:T, name:String) {
        try {
            val path: String = Directory + "/" + name
            val file: File = new File(path)
            if (!file.exists && !file.createNewFile) {
                throw new RuntimeException(String.format("Failed to create file '%s' because <unknown>.", path))
            }
            val writer: PrintWriter = new PrintWriter(path)
            writer.print(json.prettyPrint(thing))
            writer.close()
        } catch {
            case e: Exception => {
                Debug.error(e.getMessage)
            }
        }
    }

    def ensureDirectory(path: String) {
        val file: File = new File(path)
        if (file.exists && !file.isDirectory) {
            throw new RuntimeException(String.format("Expected file '%s' to be a directory. If it is safe to do so, rename that file or delete it and try again.", path))
        } else if (!file.exists && !file.mkdir) {
            throw new RuntimeException(String.format("Failed to create directory '%s' because <unknown>.", path))
        }
    }
}