package com.explatcreations.gleany.files

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle

class Files(resolver:PathResolver) {

    private def throwFileNotFound(path:String):Nothing = {
        throw new RuntimeException("failed to find file \"" + path + "\"")
    }

    private def exists(path:String) = {
        Gdx.files.internal(path).exists()
    }

    private def getFile(path:String) = {
        val file = Gdx.files.internal(path)
        if (!file.exists()) {
            throwFileNotFound(path)
        }
        file
    }

    def shader(name:String):FileHandle = {
        getFile(resolver.ShaderPath + "/" + name)
    }

    def font(name:String) = {
        getFile(resolver.FontPath + "/" + name)
    }

    def texture(name:String) = {
        getFile(resolver.TexturePath + "/" + name + ".png")
    }


    private def findSound(name:String, root:String):FileHandle = {
        val pathList = List("ogg", "wav") map {
            ext =>
                val path = root + "/" + name + "." + ext
                if (exists(path)) {
                    Some(path)
                } else {
                    None
                }
        }
        (pathList.flatten map {path => getFile(path)}).headOption.getOrElse(throwFileNotFound(name))
    }

    def music(name:String) = {
        findSound(name, resolver.MusicPath)
    }

    def sfx(name:String) = {
        findSound(name, resolver.SfxPath)
    }
}
