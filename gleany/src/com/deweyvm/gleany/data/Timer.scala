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
  def timer[T](f:() => T):(Long, T) = {
    val before = System.nanoTime
    val result = f()
    val after = System.nanoTime
    (after - before, result)
  }

  def printMillis[T](f:() => T):T = {
    printMillisString("", f)
  }

  def printMillisString[T](s:String, f:() => T):T = {
    val (ns, t) = timer(f)
    println(s + ns/1000000L + " ms")
    t
  }

  def printNanos[T](f:() => T):T = {
    printNanosString("", f)
  }

  def printNanosString[T](s:String, f:() => T):T = {
    val (ns, t) = timer(f)
    println(s + ns + " ns")
    t
  }
}
