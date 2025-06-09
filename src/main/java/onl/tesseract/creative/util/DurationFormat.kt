package onl.tesseract.creative.util

import java.time.Duration
import kotlin.math.floor

object DurationFormat {
    fun formatTime(duration: Duration): String {
        val nbDays = duration.toDays()
        val years = (nbDays/365.25).toInt()
        val months = (nbDays/30.4375).toInt() % 12
        val days = floor(nbDays%30.4375).toInt()
        val hours = (duration.toHours() % 24)
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60
        val shours = if(hours < 10) "0$hours" else hours.toString()
        val sminutes = if(minutes < 10) "0$minutes" else minutes.toString()
        val sseconds = if(seconds < 10) "0$seconds" else seconds.toString()
        val parts = mutableListOf<String>()
        if (years > 0) parts.add("$years" + "a")
        if (months > 0 || years > 0) parts.add("$months" + "mo")
        if (days > 0 || months > 0 || years > 0) parts.add("$days" + "j")
        if (hours > 0 || days > 0 || months > 0 || years > 0) parts.add(shours + "h")
        if (minutes > 0 || hours > 0 || days > 0 || months > 0 || years > 0) parts.add(sminutes + "m")
        parts.add(sseconds+"s")
        return parts.joinToString(" ")
    }
}