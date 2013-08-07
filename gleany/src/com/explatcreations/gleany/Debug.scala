package com.explatcreations.gleany

object Debug {
    val debugLoadingSequence = false
    def debug(string:String, stackOffset:Int=2) {
        val callStack = Thread.currentThread().getStackTrace
        val className = callStack(stackOffset).getClassName.split("""[.]""").last.replace("$", "")
        println("%s: %s".format(className, string))
    }

    def load() {
        if (debugLoadingSequence) {
            debug("loaded", 3)
        }
    }

    def error(string:String) {
        System.err.println(string)
    }
}