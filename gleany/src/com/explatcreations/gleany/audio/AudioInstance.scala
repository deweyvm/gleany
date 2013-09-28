package com.explatcreations.gleany.audio

abstract class AudioInstance(manager:AudioManager) {
    manager.register(this)
    def play()
    def isPlaying:Boolean
    def setVolume(newVolume:Float)
    def stop()
    def pause() 
    def resume()
}
