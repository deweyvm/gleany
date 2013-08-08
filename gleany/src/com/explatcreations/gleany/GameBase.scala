package com.explatcreations.gleany

import com.badlogic.gdx.{Application, Gdx, ApplicationListener}

class GameBase extends ApplicationListener {

    override def create() {
        Gdx.app.setLogLevel(Application.LOG_NONE)
    }
    override def render() { }
    override def dispose() { }
    override def pause() { }
    override def resume() { }
    override def resize(width:Int, height:Int) { }
}
