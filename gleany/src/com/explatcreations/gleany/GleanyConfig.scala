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

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.explatcreations.gleany.graphics.display.Display
import com.explatcreations.gleany.saving.{AudioSettings, VideoSettings}
import com.badlogic.gdx.Files


case class GleanyConfig(
        settings: VideoSettings with AudioSettings,
        title: String = "GleanyGame",
        iconPath: Option[String]=None) {
    def toLwjgl: LwjglApplicationConfiguration = lwjglConfig

    private val lwjglConfig = {
        val config = new LwjglApplicationConfiguration
        config.title = title
        config.vSyncEnabled = true
        config.useGL20 = true
        iconPath foreach { path =>
            config.addIcon(path, Files.FileType.Internal)
        }
        val desktopSize = java.awt.Toolkit.getDefaultToolkit.getScreenSize
        settings.getDisplayType match {
            case Display.Fullscreen =>
                config.width = desktopSize.width
                config.height = desktopSize.height
                config.fullscreen = true
            case Display.WindowedFullscreen =>
                config.width = desktopSize.width
                config.height = desktopSize.height
                config.resizable = false
                System.setProperty("org.lwjgl.opengl.Window.undecorated","true")
                config.fullscreen = false
            case Display.Windowed =>
                val size = settings.getWindowSize
                config.width = size.x
                config.height = size.y
                config.fullscreen = false
        }
        config
    }

}
