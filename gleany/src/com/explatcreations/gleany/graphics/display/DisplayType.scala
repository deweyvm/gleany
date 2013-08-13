package com.explatcreations.gleany.graphics.display

import com.badlogic.gdx.Gdx
import org.lwjgl.opengl.{Display => LwjglDisplay}
import com.explatcreations.gleany.data.Point2i

object DisplayType {
    def fromInt(int:Int) = Display.All(int)//fixme error handling
}

abstract class DisplayType(id:Int) {
    def toInt = id
}

object Display {
    val All = Vector(Windowed, Fullscreen, WindowedFullscreen)
    case object Windowed extends DisplayType(0)
    case object Fullscreen extends DisplayType(1)
    case object WindowedFullscreen extends DisplayType(2)

    def setDisplayMode(mode:DisplayType, defaultWindowSize:Point2i) {
        mode match {
            case Fullscreen =>
                val desktopMode = Gdx.graphics.getDesktopDisplayMode
                Gdx.graphics.setDisplayMode(desktopMode.width, desktopMode.height, true)
            case Windowed =>
                System.setProperty("org.lwjgl.opengl.Window.undecorated","false")
                LwjglDisplay.setResizable(true)
                Gdx.graphics.setDisplayMode(defaultWindowSize.x, defaultWindowSize.y, false)
            case WindowedFullscreen =>
                val desktopMode = Gdx.graphics.getDesktopDisplayMode
                System.setProperty("org.lwjgl.opengl.Window.undecorated","true")
                LwjglDisplay.setResizable(false)
                Gdx.graphics.setDisplayMode(desktopMode.width, desktopMode.height, false)
        }

    }


}
