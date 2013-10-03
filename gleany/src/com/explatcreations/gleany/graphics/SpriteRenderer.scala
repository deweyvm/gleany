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

package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import scala.collection.mutable.ArrayBuffer

class SpriteRenderer {
    val batch = new SpriteBatch(5000)

    var draws:ArrayBuffer[() => Unit] = ArrayBuffer[() => Unit]()

    def addCall(call:() => Unit) {
        draws += call
    }

    def draw(sprite:Sprite) {
        addCall (() => sprite.draw(batch))
    }

    /*def draw(fontCache:HackFontCache, start:Int, end:Int) {
        addCall(() => fontCache.draw(batch, start, end))
    }

    def draw(fontCache:HackFontCache) {
        addCall(() => fontCache.draw(batch))
    }*/
}
