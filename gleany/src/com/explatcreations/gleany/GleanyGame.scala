package com.explatcreations.gleany

import com.badlogic.gdx.{Application, Gdx, ApplicationListener}
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.explatcreations.gleany.graphics.{Color, RectSprite, Renderer}

object Singletons {
    val renderer = new Renderer
}

object GleanyGame {
    def exit() {
        Gdx.app.exit()
    }

    def runGame(config:GleanyConfig, game:GleanyGame) {
        new LwjglApplication(game, config.toLwjgl)
    }
}

abstract class GleanyGame extends ApplicationListener {

    def update()
    def draw()

    override def create() {
        Gdx.app.setLogLevel(Application.LOG_NONE)
    }
    override def render() {
        update()
        draw()
        Singletons.renderer.drawBatch(() => {
            new RectSprite(10,10,Color.Blue).draw(0,0)
            ()
        }
        )
    }
    override def dispose() { }
    override def pause() { }
    override def resume() { }
    override def resize(width:Int, height:Int) { }
}
