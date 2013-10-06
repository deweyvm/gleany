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

object FluidParams {
  def default = FluidParams(
    Timestep = 0.016666f,
    MaxParticles = 6000,
    MaxNeighbors = 150,
    InteractionRadius = 20,
    LinearViscosity = 0,
    QuadraticViscosity = 0.3f,
    RestDensity = 10,
    Stiffness = 0.504f,
    NearStiffnessFactor = 10,
    MaxVelocity = 150f)
}

case class FluidParams(
                          Timestep: Float,
                          MaxParticles: Int,
                          MaxNeighbors: Int,
                          InteractionRadius: Float,
                          LinearViscosity: Float,
                          QuadraticViscosity: Float,
                          RestDensity: Float,
                          Stiffness: Float,
                          NearStiffnessFactor: Float,
                          MaxVelocity: Float)
