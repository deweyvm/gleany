package com.explatcreations.gleany

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.explatcreations.gleany.graphics.display.Display
import com.explatcreations.gleany.loading.VideoSettings


case class GleanyConfig(videoSettings:VideoSettings, title:String = "GleanyGame") {
    def toLwjgl:LwjglApplicationConfiguration = lwjglConfig

    private val lwjglConfig = {
        val config = new LwjglApplicationConfiguration
        config.title = title
        config.vSyncEnabled = true
        config.useCPUSynch = true
        config.useGL20 = true
        val desktopSize = java.awt.Toolkit.getDefaultToolkit.getScreenSize
        videoSettings.getDisplayType match {
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
                val size = videoSettings.getWindowSize
                config.width = size.x
                config.height = size.y
                config.fullscreen = false
        }
        config
    }

}
