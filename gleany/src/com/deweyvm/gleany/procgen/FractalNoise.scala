package com.deweyvm.gleany.procgen

import com.deweyvm.gleany.utils.PNG
import scala.collection.immutable.IndexedSeq
import com.badlogic.gdx.graphics.{Pixmap, Texture}
import java.io.{FileOutputStream, File}
import scala.util.Random
import com.deweyvm.gleany.graphics.Color

object FractalNoise {
  def makeTexture: Texture = {
    new FractalNoise().makePixmap(pixmap => {
      new Texture(pixmap)
    })
  }
}

class FractalNoise {
  private val whatDoesThisVariableMean = 256
  private val perm: IndexedSeq[Int] = {
    val t: IndexedSeq[Int] = new Random().shuffle(0 until whatDoesThisVariableMean : IndexedSeq[Int])
    t ++ t
  }
  private val pi = 3.1415926535897932384626433
  private val dirs = (0 until whatDoesThisVariableMean) map { i =>
    val x = scala.math.cos(i * 2 * pi/whatDoesThisVariableMean)
    val y = scala.math.sin(i * 2 * pi/whatDoesThisVariableMean)
    (x, y)
  }

  private def noise(x: Double, y: Double, per: Int): Double = {
    import scala.math._
    def surflet(gridX: Double, gridY: Double): Double = {
      val (distX, distY) = (abs(x - gridX), abs(y - gridY))
      def poly(v: Double) = 1 - 6*pow(v, 5) + 15*pow(v, 4) - 10*pow(v, 3)
      val polyX = poly(distX)
      val polyY = poly(distY)
      val hashed = perm(perm(gridX.toInt%per) + gridY.toInt%per)
      val grad = (x-gridX)*dirs(hashed)._1 + (y-gridY)*dirs(hashed)._2
      polyX * polyY * grad
    }
    val (intX, intY) = (x.toInt, y.toInt)
    surflet(intX,     intY    ) +
      surflet(intX + 1, intY    ) +
      surflet(intX,     intY + 1) +
      surflet(intX + 1, intY + 1)
  }

  private def fBm(x: Double, y: Double, per: Int, octs: Int): Double = {
    import scala.math._
    var v = 0.0
    for (i <- 0 until octs) {
      val p = pow(2, i).toInt
      val xArg: Double = x * p
      val yArg: Double = y * p
      v += pow(0.5, i).toFloat * noise(xArg, yArg, per * p)
    }
    v
  }

  private def savePixmap(pixmap:Pixmap, filename:String, width:Int, height:Int) {
    val bytes = PNG.write(pixmap)
    val file = new File(filename)
    val stream = new FileOutputStream(file)
    stream.write(bytes)
    stream.close()
  }

  def testImage(size: Int, freq: Double, octs: Int, filename: String) {
    makePixmap((pixmap: Pixmap) => {
      savePixmap(pixmap, filename, size, size)
    })
  }

  def render:Array[Array[Double]] = {
    val freq = 1/32.0
    val size = 256
    val octs = 5
    Array.tabulate(size, size) { case (x, y) =>
      val r = fBm(freq*x, freq*y, (size*freq).toInt, octs)
      val c = (r + 1.0)/2.0 - 0.2
      c
    }
  }

  private def makePixmap[T](func: Pixmap => T): T = {
    val freq = 1/32f
    val size = 256
    val octs = 5
    val pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888)
    for (x <- 0 until size; y <- 0 until size) {
      val r = fBm(freq*x, freq*y, (size*freq).toInt, octs).toFloat
      val c = (r + 1f)/2f - 0.2f
      val a = if (c < 0) 0 else c
      val color = new Color(c, c, c, a)
      pixmap.setColor(color.toLibgdxColor)
      pixmap.drawPixel(x, y)
    }
    val result = func(pixmap)
    pixmap.dispose()
    result
  }

}
