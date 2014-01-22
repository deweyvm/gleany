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

import com.deweyvm.gleany.Implicits._


object EnrichedString {
  def test() {
    val tests = Vector(
      ("this|is|a|test", '|', 4),
      ("", '|', 1),
      ("|", '|', 2),
      ("abc|", '|', 2)
    )

    tests foreach { case (line, sep, count) =>
      val sp = line.esplit(sep)
      printf("line to split: <%s>\n", line)
      for (s <- sp) {
        printf("    <%s>\n", s)
      }
      printf("%d ==? %d\n\n", sp.length, count)
      assert(sp.length == count)
    }
  }
}

class EnrichedString(rep:String) {
  /**
   * Like the java string split, but a split at the end of the string leaves an additional
   * empty string in the output. See test methods for an example.
   * @param sep the separator
   * @return the strings existing around occurrences of rep in order
   */
  def esplit(sep:Char):Vector[String] = {
    val (lines, last) = rep.foldLeft(Vector[String](), "") { case ((lines,  l), c) =>
      if (c == sep) {
        (lines ++ Vector(l), "")
      } else {
        (lines, l + c)
      }
    }
    lines ++ Vector(last)
  }


}
