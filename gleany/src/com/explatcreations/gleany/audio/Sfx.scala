package com.explatcreations.gleany.audio

import com.badlogic.gdx.audio

class Sfx(manager:AudioManager, sound:audio.Music, looped:Boolean) extends AudioInstance(manager) {
    private var adjVolume = 1f
    override def play() {
        manager += this
        sound.setLooping(looped)
        sound.play()
    }

    def setAdjustVolume(adjVol:Float) {
        adjVolume = adjVol
        setVolume(manager.settings.getSfxVolume)
    }

    override def isPlaying = {
        sound.isPlaying
    }

    override def setVolume(newVolume:Float) {
        sound.setVolume(newVolume*adjVolume)
    }

    override def pause() {
        sound.pause()
    }

    override def stop() {
        sound.stop()
    }

    override def resume() {
        sound.play()
    }

    setVolume(manager.settings.getSfxVolume)
}
