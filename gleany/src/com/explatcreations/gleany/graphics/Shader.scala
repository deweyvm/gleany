package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Matrix4
import com.explatcreations.gleany.Debug

object Shader {
    private val Root = "data/shaders/"
}

class Shader(vert:String, frag:String) {
    import Shader._
    private val actions = scala.collection.mutable.Map[String, () => Unit]()
    
    val shader = compileShader()
    
    private def compileShader() = {
        
        val result = new ShaderProgram(Gdx.files.internal(Root + vert),
                                       Gdx.files.internal(Root + frag))
        val log = result.getLog
        //disallow any warnings
        if ( !result.isCompiled || (log.length() != 0 && log.contains("warning"))) {
            Debug.error(log)
            throw new RuntimeException()
        }

        result
    }

    private def makeAction(name:String, func:() => Unit) {
        actions(name) = () => {
            if (shader.hasUniform(name)) {
                func()
            }
        }
    }

    def setMat(name:String, func:() => Matrix4) {
        val setter = () => shader.setUniformMatrix(name, func())
        makeAction(name, setter)
    }

    def setInt(name:String, func: () => Int) {
        val setter = () => shader.setUniformi(name, func())
        makeAction(name, setter)
    }

    def setFloat(name:String, func: () => Float) {
        val setter = () => shader.setUniformf(name, func())
        makeAction(name, setter)
    }


    def setFloat2(name:String, func: () => (Float,Float)) {
        val setter = () => {
            val (x, y) = func()
            shader.setUniformf(name, x, y)
        }
        makeAction(name, setter)
    }

    
    private def begin() {
        shader.begin()
        actions.values.foreach(_())
    }
    
    private def end() {
        shader.end()
    }
    
    def draw(func:() => Unit) {
        begin()
        func()
        end()
    }
}

