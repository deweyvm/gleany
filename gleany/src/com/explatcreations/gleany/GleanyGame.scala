package com.explatcreations.gleany

import com.badlogic.gdx.{Application, Gdx, ApplicationListener}
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.explatcreations.gleany.saving.{VideoSettings, AudioSettings}

object Gleany {
    //var gleany:Gleany = null
}

class Gleany(val settings:AudioSettings with VideoSettings) {
    //val audio = new AudioManager(settings)
}

object GleanyGame {
    def exit() {
        Gdx.app.exit()
    }

    /**
     * this function does not return
     */
    def runGame(config:GleanyConfig, game:GleanyGame) {
        new LwjglApplication(game, config.toLwjgl)
    }
}

abstract class GleanyGame/*(initializer:GleanyInitializer)*/ extends ApplicationListener {

    def update()
    def draw()

    override def create() {
        Gdx.app.setLogLevel(Application.LOG_NONE)
        //Gleany.gleany = initializer.create
    }

    override def render() {
        update()
        draw()
    }
    override def dispose() { }
    override def pause() { }
    override def resume() { }
    override def resize(width:Int, height:Int) { }
}
