/******************************************************************************
 * Copyright 2013, deweyvm
 *
 * This file is part of Gleany.
 *
 * Gleany is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Gleany is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along
 * with Gleany.
 *
 * If not, see <http://www.gnu.org/licenses/>.
 *****************************************************************************/

package com.explatcreations.gleany

import com.badlogic.gdx.{Application, Gdx, ApplicationListener}
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.explatcreations.gleany.saving.{Settings, VideoSettings, AudioSettings}
import com.explatcreations.gleany.files.{PathResolver, Files}
import com.explatcreations.gleany.audio.AudioManager
import com.explatcreations.gleany.loading.GleanyTiledMap

object Glean {
    var y:Gleany = null
}

class Gleany(resolver:PathResolver, val settings:Settings) {
    val audio = new AudioManager(settings)
    val files = new Files(resolver)

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

abstract class GleanyGame(initializer:GleanyInitializer) extends ApplicationListener {

    def gleanyUpdate() {
        Glean.y.audio.update()
    }

    def update()
    def draw()

    override def create() {
        Gdx.app.setLogLevel(Application.LOG_NONE)
        Glean.y = new Gleany(initializer.pathResolver, initializer.settings)

        println(new GleanyTiledMap("room+000+000.tmx").toString)
    }

    override def render() {
        gleanyUpdate()
        update()
        draw()
    }

    override def dispose() { }
    override def pause() { }
    override def resume() { }
    override def resize(width:Int, height:Int) { }
}
