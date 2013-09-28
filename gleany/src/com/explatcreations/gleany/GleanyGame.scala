/*
 * Copyright 2013, deweyvm
 *
 * This file is part of Gleany.
 *
 * Gleany is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Gleany is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Gleany.
 * If not, see <http://www.gnu.org/licenses/>.
 */

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
