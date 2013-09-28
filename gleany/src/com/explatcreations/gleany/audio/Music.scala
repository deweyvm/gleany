package com.explatcreations.gleany.audio

import com.badlogic.gdx.audio

class Music(manager:AudioManager, music:audio.Music) extends AudioInstance(manager) {
    private var adjVolume = 1f
    override def play() {
        manager += this
        music.play()
    }

    override def isPlaying = {
        music.isPlaying
    }

    def setAdjustVolume(adjVol:Float) {
        adjVolume = adjVol
        setVolume(manager.settings.getMusicVolume)
    }

    override def setVolume(newVolume:Float) {
        music.setVolume(newVolume*adjVolume)
    }

    override def stop() {

    }

    override def pause() {
        music.pause()
    }

    override def resume() {
        music.play()
    }


    setVolume(manager.settings.getMusicVolume)
    music.setLooping(true)
}
