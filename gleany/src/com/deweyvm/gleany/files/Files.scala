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

package com.deweyvm.gleany.files

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle

class Files(resolver: PathResolver) {
  private def throwFileNotFound(path: String): Nothing = {
    throw new RuntimeException("failed to find file \"" + path + "\"")
  }

  private def exists(path: String) = Gdx.files.internal(path).exists()

  private def getFile(path: String) = {
    val file = Gdx.files.internal(path)
    if (!file.exists()) {
      throwFileNotFound(path)
    }
    file
  }

  private def getMapPath(name: String) = resolver.MapPath + "/" + name + ".tmx"

  def mapExists(name: String): Boolean = exists(getMapPath(name))

  def map(name: String): FileHandle = getFile(getMapPath(name))

  def shader(name: String): FileHandle = getFile(resolver.ShaderPath + "/" + name)

  def font(name: String): FileHandle = getFile(resolver.FontPath + "/" + name)

  def texture(name: String): FileHandle = getFile(resolver.TexturePath + "/" + name + ".png")

  def data(name: String): FileHandle = getFile(resolver.DataPath + "/" + name)

  private def findSound(name: String, root: String): FileHandle = {
    val pathList = List("ogg", "wav", "mp3") map {
      ext =>
        val path = root + "/" + name + "." + ext
        if (exists(path)) {
          Some(path)
        } else {
          None
        }
    }
    pathList.flatten.map(getFile).headOption.getOrElse(throwFileNotFound(name))
  }

  def music(name: String): FileHandle = findSound(name, resolver.MusicPath)

  def sfx(name: String): FileHandle = findSound(name, resolver.SfxPath)

}
