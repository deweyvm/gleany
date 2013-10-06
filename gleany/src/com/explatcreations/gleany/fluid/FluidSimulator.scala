/** ****************************************************************************
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
  * ****************************************************************************/

package com.explatcreations.gleany.fluid

import collection.mutable.ArrayBuffer
import com.badlogic.gdx.{Input, Gdx}
import com.explatcreations.gleany.GleanyMath

/**
 * Viscoelastic fluid simulation.
 * Based on "Particle-based Viscoelastic Fluid Simulation"
 * by Simon Clavet, Philippe Beaudoin, Pierre Poul
 *
 * Code based on Simone Autore's lovely java implementation available at
 * https://github.com/omgware/fluid-simulator
 * @author deweyvm
 */

class FluidSimulator(params: FluidParams, worldWidth: Int, worldHeight: Int) {
  val GridScale: Int = 4
  val BufferX: Int = -10
  val BufferY: Int = 0
  val cols: Int = worldWidth / GridScale
  val rows: Int = worldHeight / GridScale

  val MaxSize: Int = params.MaxParticles
  val MaxNeighbors: Int = params.MaxNeighbors
  val VelocityCap: Float = params.MaxVelocity

  val InteractionRadius: Float = params.InteractionRadius
  val InteractionRadius2: Float = InteractionRadius * InteractionRadius
  val LinearViscosity: Float = params.LinearViscosity
  val QuadraticViscosity: Float = params.QuadraticViscosity
  val Stiffness: Float = params.Stiffness
  val NearStiffness: Float = params.NearStiffnessFactor

  val RestDensity: Float = params.RestDensity
  val Timestep: Float = params.Timestep
  val Timestep2: Float = Timestep * Timestep

  val AttractForce: Float = cols / 300f
  val AttractRange: Float = cols * 2f
  val RepulseForce: Float = cols / 1f
  val RepulseRange: Float = cols / 2f

  private val deleteQueue = ArrayBuffer[Drop]()
  private val particles = makeParticles

  private def makeParticles = {
    val particles = new FluidTable(cols, rows, MaxNeighbors * 2 + 1)
    val spread = 64f
    for (i <- 0 until (MaxSize / spread).toInt; j <- 0 until spread.toInt) {
      val prop = i / MaxSize.toFloat
      val x = prop * (cols * spread)
      val y = (j / spread) * rows
      val drop = new Drop(x, y)
      particles += drop
    }
    particles
  }

  def length: Int = particles.length

  def foreach(func: Drop => Unit) {
    particles foreach func
  }

  private def performLogic() {
    applyViscosity()
    particles foreach updateParticle
    particles.rehash()
    doubleDensityRelaxation()
    applyForces()
  }

  private def updateParticle(drop: Drop) {
    drop.xPrev = drop.x
    drop.yPrev = drop.y
    drop.x += drop.vx * Timestep
    drop.y += drop.vy * Timestep
  }

  private def applyForces() {
    particles foreach {
      drop =>
        drop.vx = (drop.x - drop.xPrev) / Timestep
        drop.vy = (drop.y - drop.yPrev) / Timestep

        applyGravity(drop)
        wallCollision(drop)
        attract(drop)
        repulse(drop)
        capVelocity(drop)
        checkBounds(drop)
        particles.updateGrid(drop)
    }
  }

  private def applyGravity(drop: Drop) {
    val randAmount = 1
    //drop.vx += scala.math.random.toFloat*randAmount*2 - randAmount
    drop.vy += scala.math.random.toFloat * randAmount * 2 - randAmount
    val heightVx = (worldHeight - drop.y) / worldHeight.toFloat
    drop.vx += heightVx

  }

  private def wallCollision(pi: Drop) {
    if (pi.x < BufferX) {
      pi.x = BufferX
      if (pi.vx < 0) {
        pi.vx = 0
      }
      pi.vx += 10
    }
    else if (pi.x > cols - BufferX) {
      pi.x = BufferX
      if (pi.vx > 0) {
        //pi.vx = 0
      }
      //pi.vx += -10
    }
    if (pi.y < BufferY) {
      pi.y = BufferY
      if (pi.vy < 0) {
        pi.vy = 0
      }
      pi.vy += 10
    }
    else if (pi.y > rows - BufferY) {
      pi.y = rows - BufferY
      if (pi.vy > 0) {
        pi.vy = 0
      }
      pi.vy += -10
    }
  }

  private def mouseX = Gdx.input.getX / getScaleX

  private def mouseY = Gdx.input.getY / getScaleY

  private def dstToMouse(pi: Drop): Float = {
    val dx = pi.x - mouseX
    val dy = pi.y - mouseY
    dx * dx + dy * dy
  }

  private def attract(pi: Drop) {
    val isAttracting: Boolean = Gdx.input.isTouched(1)
    if (!isAttracting || dstToMouse(pi) > AttractRange) {
      return
    }
    val vx = mouseX - pi.x
    val vy = mouseY - pi.y
    pi.vx += vx * AttractForce
    pi.vy += vy * AttractForce
  }

  private def repulse(pi: Drop) {
    val isRepulsing: Boolean = Gdx.input.isTouched(2)
    if (!isRepulsing || dstToMouse(pi) > RepulseRange) {
      return
    }
    val vx = pi.x - mouseX
    val vy = pi.y - mouseY
    pi.vx = vx * RepulseForce
    pi.vy = vy * RepulseForce
  }

  private def capVelocity(drop: Drop) {
    val height = rows + 20f
    val vxMax = scala.math.abs(((height - drop.y) / height) * VelocityCap)
    drop.vx = GleanyMath.clamp(drop.vx, 0, vxMax)
    drop.vy = GleanyMath.clamp(drop.vy, -VelocityCap, VelocityCap)
  }

  private def checkBounds(pi: Drop) {
    val padding: Int = 20
    if ((pi.x < -padding)
        || (pi.x > cols + padding)
        || (pi.y < -padding)
        || (pi.y > rows + padding)) {
      pi.x = 30
      pi.y = 30
    }
  }

  private def dst2(dropi: Drop, dropj: Drop) = {
    val dx = dropi.x - dropj.x
    val dy = dropi.y - dropj.y
    dx * dx + dy * dy
  }

  private def applyViscosity() {
    particles foreach {
      pi: Drop =>
        val nearParticles = particles.getAdjacent(pi)
        val len = scala.math.min(nearParticles.length, MaxNeighbors)

        (0 until len) foreach {
          j: Int =>
            val pj = nearParticles(j)
            var q = dst2(pi, pj)
            if (q < InteractionRadius2 && q != 0) {
              q = scala.math.sqrt(q).toFloat
              var rijx = (pj.x - pi.x) / q
              var rijy = (pj.y - pi.y) / q

              val dvx = (pi.vx - pj.vx) * rijx
              val dvy = (pi.vy - pj.vy) * rijy
              val u = dvx + dvy

              if (u > 0) {
                q /= InteractionRadius
                val I = (0.5f * Timestep * (1 - q)
                    * (u * LinearViscosity
                    + u * u * QuadraticViscosity))
                rijx *= I
                rijy *= I

                pi.vx -= rijx
                pi.vy -= rijy

                pj.vx += rijx
                pj.vy += rijy
              }
            }
        }
    }
  }


  private def doubleDensityRelaxation() {
    //cache distance and distance squared
    val d2 = new Array[Float](MaxNeighbors)
    val d = new Array[Float](MaxNeighbors)

    particles foreach {
      pi: Drop =>
        pi.density = 0
        pi.nearDensity = 0
        val nearParticles = particles.getAdjacent(pi)
        val len = scala.math.min(nearParticles.length, MaxNeighbors)
        (0 until len) foreach {
          j =>
            var q = dst2(pi, nearParticles(j))
            d2(j) = q
            if (q < InteractionRadius2 && q != 0) {
              q = scala.math.sqrt(q).toFloat
              d(j) = q
              q /= InteractionRadius
              val qq = (1 - q) * (1 - q)
              pi.density += qq
              pi.nearDensity += qq * (1 - q)
            }
        }

        pi.pressure = Stiffness * (pi.density - RestDensity)
        pi.nearPressure = NearStiffness * pi.nearDensity
        var ddx = 0f
        var ddy = 0f
        (0 until len) foreach {
          j =>
            val pj = nearParticles(j)
            var q = d2(j)
            if (q < InteractionRadius2 && q != 0) {
              q = d(j)
              var rijx = (pj.x - pi.x) / q
              var rijy = (pj.y - pi.y) / q
              q /= InteractionRadius

              val D = (0.5f
                  * Timestep2
                  * ((1 - q) * pi.pressure
                  + (1 - q) * (1 - q) * pi.nearPressure))
              rijx *= D
              rijy *= D

              pj.x += rijx
              pj.y += rijy
              ddx -= rijx
              ddy -= rijy
            }
        }
        pi.x += ddx
        pi.y += ddy
    }
  }

  def update() {
    performLogic()
    processInput()
  }

  private def processInput() {
    if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
      if (Gdx.input.isTouched(1)) {
        for (pi <- particles) {
          if (dstToMouse(pi) < AttractRange) {
            //deleteQueue.add(pi)
          }
        }
      }
      else {
        particles.clear()
      }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
      Gdx.app.exit()
    }

    processAddParticles()
  }

  def getScaleX: Float = worldWidth / cols.toFloat

  def getScaleY: Float = worldHeight / rows.toFloat

  private def processAddParticles() {
    if (!Gdx.input.isTouched(0)
        || particles.length >= MaxSize - 1) {
      return
    }

    for (i <- 0 until 10; j <- 0 until 10) {
      val newp: Drop = new Drop(mouseX + i, mouseY + j)
      particles += newp
    }
  }

}
