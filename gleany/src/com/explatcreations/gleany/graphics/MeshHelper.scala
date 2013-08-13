package com.explatcreations.gleany.graphics

import com.badlogic.gdx.graphics.Mesh
import com.badlogic.gdx.graphics.VertexAttribute
import com.badlogic.gdx.graphics.VertexAttributes.Usage

object MeshHelper {
    def makeMesh = {
        val mesh = new Mesh(true, 4, 0,
            new VertexAttribute(Usage.Position, 3, "a_position" ),
            new VertexAttribute(Usage.TextureCoordinates, 2, "a_texCoords"))
        val vertices = Array[Float](-1f, -1f, 0, 0, 1,
                                     1f, -1f, 0, 1, 1,
                                     1f,  1f, 0, 1, 0,
                                    -1f,  1f, 0, 0, 0)
        mesh.setVertices(vertices)
        mesh
    }
}