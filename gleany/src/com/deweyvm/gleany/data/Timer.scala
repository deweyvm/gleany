/******************************************************************************
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
 *****************************************************************************/

package com.deweyvm.gleany.data

object Timer {


  def timer[T](f:() => T):(T, Long) = {
    val before = System.nanoTime
    val result = f()
    (result, System.nanoTime - before)
  }

  def printMillis[T](f:() => T):T = {
    printMillisString("", f)
  }

  def printMillisString[T](s:String, f:() => T):T = {
    val before = System.nanoTime
    val result = f()
    println(s + (System.nanoTime - before)/1000000L + " ms")
    result
  }

  def printNanos[T](f:() => T):T = {
    val before = System.nanoTime
    val result = f()
    println((System.nanoTime - before) + " ns")
    result
  }
}
