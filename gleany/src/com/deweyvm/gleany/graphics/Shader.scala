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

package com.deweyvm.gleany.graphics

import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.math.Matrix4
import com.deweyvm.gleany.{Glean, Debug}
import com.badlogic.gdx.graphics.{GL20, Texture, Mesh}
import com.badlogic.gdx.Gdx

object Shader {
  def cleanupTextures(numTextures: Int) {
    for (i <- 0 until numTextures) {
      Gdx.gl.glActiveTexture(GL20.GL_TEXTURE1 + i)
      Gdx.gl.glBindTexture(GL20.GL_TEXTURE_2D, 0)
    }
    Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0)
  }
}

class Shader(vert: String, frag: String) {
  private val actions = scala.collection.mutable.Map[String, () => Unit]()
  private val mesh = MeshHelper.makeMesh
  val shader:ShaderProgram = compileShader()

  private def compileShader() = {
    val result = new ShaderProgram(Glean.y.files.shader(vert), Glean.y.files.shader(frag))
    val log = result.getLog
    //disallow any warnings
    if (   !result.isCompiled
        || (log.length() != 0 && log.contains("warning"))) {
      Debug.error(log)
      throw new RuntimeException()
    }

    result
  }

  private def makeAction(name: String, func: () => Unit) {
    actions(name) = () => {
      if (shader.hasUniform(name)) {
        func()
      }
    }
  }

  def setMat(name: String, func: () => Matrix4) {
    val setter = () => shader.setUniformMatrix(name, func())
    makeAction(name, setter)
  }

  def setInt(name: String, func: () => Int) {
    val setter = () => shader.setUniformi(name, func())
    makeAction(name, setter)
  }

  def setFloat(name: String, func: () => Float) {
    val setter = () => shader.setUniformf(name, func())
    makeAction(name, setter)
  }


  def setFloat2(name: String, func: () => (Float, Float)) {
    val setter = () => {
      val (x, y) = func()
      shader.setUniformf(name, x, y)
    }
    makeAction(name, setter)
  }

  def setFloat4(name: String, func: () => (Float, Float, Float, Float)) {
    val setter = () => {
      val (x, y, z, w) = func()
      shader.setUniformf(name, x, y, z, w)
    }
    makeAction(name, setter)
  }


  private def begin() {
    shader.begin()
    actions.values.foreach(_())
  }

  private def end() {
    shader.end()
  }

  def draw(func: () => Unit) {
    begin()
    func()
    mesh.render(shader, GL20.GL_TRIANGLE_FAN)
    end()
  }

  def draw(binds:(Texture, Int)*) {
    begin()
    binds foreach { case (texture, index) =>
      texture.bind(index)
    }
    mesh.render(shader, GL20.GL_TRIANGLE_FAN)
    end()
    if (binds.length > 0) {
      Shader.cleanupTextures(binds.length)
    }
    ()
  }
}

