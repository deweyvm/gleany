package com.explatcreations.gleany.loading

import com.badlogic.gdx.utils.XmlReader
import com.badlogic.gdx.Gdx

class GleanyTiledMap(name:String) {
    val xml = new XmlReader()
    val element = xml.parse(Gdx.files.internal(name))

}
