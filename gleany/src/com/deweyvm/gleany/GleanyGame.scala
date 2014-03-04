/** ****************************************************************************
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
  * ****************************************************************************/

package com.deweyvm.gleany

import com.badlogic.gdx.{Application, Gdx, ApplicationListener}
import com.badlogic.gdx.backends.lwjgl.LwjglApplication


object GleanyGame {
  def exit() {
    Gdx.app.exit()
  }

  /**
   * this function does not return while the game is running
   */
  def runGame(config: GleanyConfig, game: GleanyGame) {
    new LwjglApplication(game, config.toLwjgl)
  }
}

abstract class GleanyGame(initializer: GleanyInitializer) extends ApplicationListener {

  def gleanyUpdate() {
    Glean.y.audio.update()
  }

  def update(): Unit

  def draw(): Unit

  override def create() {
    Gdx.app.setLogLevel(Application.LOG_NONE)
    Glean.y = new Gleany(initializer.pathResolver, initializer.settings)
  }

  override def render() {
    gleanyUpdate()
    update()
    draw()
  }

  override def dispose() {}

  override def pause() {}

  override def resume() {}

  override def resize(width: Int, height: Int) {}
}

