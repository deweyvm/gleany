package com.explatcreations.gleany.files

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle

class Files(resolver:PathResolver) {
    def shader(name:String):FileHandle = {
        Gdx.files.internal(resolver.ShaderPath + "/" + name)
    }

    def font(name:String) = {
        Gdx.files.internal(resolver.FontPath + "/" + name)
    }

    def texture(name:String) = {
        Gdx.files.internal(resolver.TexturePath + "/" + name + ".png")
    }

    def music(name:String) = {
        List("ogg", "wav") map {
            ext =>
                val file = Gdx.files.internal(resolver.MusicPath + "/" + name + "." + ext)
                if (file.exists()) {
                    Some(file)
                } else {
                    None
                }
        }
    }
}
