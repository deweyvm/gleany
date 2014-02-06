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

import java.util.{Date, Calendar, TimeZone}

object Time {
  def getString:String = {
    epochTime.toString
  }

  def epochTime:Int = {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    (calendar.getTimeInMillis / 1000L).toInt
  }

  def epochToDate(epoch:Int):String = {
    val millis:Long = epoch.toLong * 1000L
    new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(millis))
  }
}
