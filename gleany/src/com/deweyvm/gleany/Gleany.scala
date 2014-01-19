/******************************************************************************
 * Copyright 2013, moopslc
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

package com.deweyvm.gleany

import com.deweyvm.gleany.files.{Files, PathResolver}
import com.deweyvm.gleany.saving.Settings
import com.deweyvm.gleany.audio.AudioManager

object Glean {
  var y: Gleany = null
}


class Gleany(resolver: PathResolver, val settings: Settings) {
  val audio:AudioManager = new AudioManager(settings)
  val files:Files = new Files(resolver)

}
