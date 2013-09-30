package com.explatcreations.gleany.files

import com.badlogic.gdx.Gdx

class Files(resolver:PathResolver) {
    def shader(name:String):String = {
        Gdx.files.internal(resolver.ShaderPath + "/" + name).readString
    }
}
