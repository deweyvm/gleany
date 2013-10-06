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

package com.explatcreations.gleany.data


object WrappedArray {
  def nonLooped[T](elements: IndexedSeq[T]): WrappedArray[T] = new WrappedArray(elements, false)
}

class WrappedArray[T](seq: IndexedSeq[T], looped: Boolean) {
  private val length = seq.length

  if (length == 0) {
    throw new RuntimeException("WrappedArray must be nonempty")
  }

  private val ptr = {
    if (looped) {
      new LoopedBoundedPointer(seq.length)
    } else {
      new BoundedPointer(seq.length)
    }
  }

  def increment() {
    ptr.increment()
  }

  def decrement() {
    ptr.decrement()
  }

  def isAtBeginning: Boolean = ptr.isAtBeginning

  def isAtEnd: Boolean = ptr.isAtEnd

  def reverse() {
    ptr.reverse()
  }

  def foreach(func: T => Unit) {
    seq foreach func
  }

  def zipWithIndex: IndexedSeq[(T, Int)] = seq.zipWithIndex

  def reset() {
    ptr.setToBeginning()
  }

  def get: T = ptr.get(seq)

  def doSelected(func: (T, Boolean) => Unit) {
    val selected = get
    foreach { t =>
      func(t, t == selected)
    }
  }

  def setToEnd() {
    ptr.setToEnd()
  }
}
