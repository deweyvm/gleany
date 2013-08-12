package com.explatcreations.gleany

import com.badlogic.gdx.{Application, Gdx, ApplicationListener}
import com.badlogic.gdx.backends.lwjgl.LwjglApplication

object GleanyGame {
    def exit() {
        Gdx.app.exit()
    }

    def runGame(config:GleanyConfig, game:GleanyGame) {
        new LwjglApplication(game, config.toLwjgl)
    }
}

class GleanyGame extends ApplicationListener {

    override def create() {
        Gdx.app.setLogLevel(Application.LOG_NONE)
    }
    override def render() { }
    override def dispose() { }
    override def pause() { }
    override def resume() { }
    override def resize(width:Int, height:Int) { }
}
