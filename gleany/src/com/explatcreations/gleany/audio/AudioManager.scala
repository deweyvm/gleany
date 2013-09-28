package com.explatcreations.gleany.audio

import collection.mutable.ArrayBuffer
import com.explatcreations.gleany.Debug
import com.explatcreations.gleany.saving.AudioSettings

class ActiveAudioCollection {
    private var playing = ArrayBuffer[AudioInstance]()
    private var paused = false
    def +=(audio:AudioInstance) {
        playing += audio
    }

    def update() {
        if (!paused) {
            playing = playing filter {_.isPlaying}
        }
    }

    def pause() {
        if (!paused) {
            paused = true
            playing foreach {_.pause()}
        }
    }

    def resume() {
        if (paused) {
            paused = false
            playing foreach {_.resume()}
        }
    }
}

class AudioManager(val settings:AudioSettings) {
    Debug.load()
    //fixme -- code clones
    private val playingSfx = new ActiveAudioCollection
    private val playingMusic = new ActiveAudioCollection
    private var sfxVolume = settings.getSfxVolume
    private var musicVolume = settings.getMusicVolume
    private val allAudio = ArrayBuffer[AudioInstance]()


    def +=(audio:Sfx) {playingSfx += audio}
    def +=(audio:Music) {playingMusic += audio}


    def register(audio:AudioInstance) {
        allAudio += audio
    }

    def update() {
        playingSfx.update()
        playingMusic.update()
    }

    def setSfxVolume(newVolume:Float) {
        sfxVolume = newVolume
        allAudio filter {_.isInstanceOf[Sfx]} foreach {_.setVolume(newVolume)}
    }

    def setMusicVolume(newVolume:Float) {
        musicVolume = newVolume
        allAudio filter {_.isInstanceOf[Music]} foreach {_.setVolume(newVolume)}
    }

    def pauseSfx() {
        playingSfx.pause()
    }

    def resumeSfx() {
        playingSfx.resume()
    }

    def pauseMusic() {
        playingMusic.pause()
    }

    def resumeMusic() {
        playingMusic.resume()
    }

    def pause() {
        pauseMusic()
        pauseSfx()
    }

    def resume() {
        resumeMusic()
        resumeSfx()
    }
}
