package com.explatcreations.gleany.loading

import com.badlogic.gdx.utils.Json
import java.io.{PrintWriter, File}
import java.util.Scanner
import com.explatcreations.gleany.Debug

object LoadUtils {
    final val Directory: String = System.getProperty("user.home") + "/.Mission"
    final val json: Json = new Json

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
            }
            catch {
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
        }
        catch {
            case e: Exception => {
                Debug.error(e.getMessage)
            }
        }
    }

    def ensureDirectory(path: String) {
        val file: File = new File(path)
        if (file.exists && !file.isDirectory) {
            throw new RuntimeException(String.format("Expected file '%s' to be a directory. If it is safe to do so, rename that file or delete it and try again.", path))
        }
        else if (!file.exists && !file.mkdir) {
            throw new RuntimeException(String.format("Failed to create directory '%s' because <unknown>.", path))
        }
    }
}