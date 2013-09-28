package com.explatcreations.gleany.fluid

object FluidParams {
    def default = FluidParams(Timestep = 0.016666f,
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

case class FluidParams(Timestep:Float,
                       MaxParticles:Int,
                       MaxNeighbors:Int,
                       InteractionRadius:Float,
                       LinearViscosity:Float,
                       QuadraticViscosity:Float,
                       RestDensity:Float,
                       Stiffness:Float,
                       NearStiffnessFactor:Float,
                       MaxVelocity:Float)
