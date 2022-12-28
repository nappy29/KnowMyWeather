package com.example.swensonheeval.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun changeDateFormat(
        currentFormat: String = "yyyy-MM-dd HH:mm",
        requiredFormat: String = "EEEE, d MMMM yyyy",
        dateString: String
    ): String {
        var result = ""
        if (dateString.isNullOrEmpty()) {
            return result
        }
        val formatterOld = SimpleDateFormat(currentFormat, Locale.getDefault())
        val formatterNew = SimpleDateFormat(requiredFormat, Locale.getDefault())
        var date: Date? = null
        try {
            date = formatterOld.parse(dateString)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date != null) {
            result = formatterNew.format(date)
        }
        return result
    }

    fun getTime(
        dateString: String
    ): String {
        if (dateString == "")
            return ""

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = dateFormatter.parse(dateString)

        val timeFormatter = SimpleDateFormat("h:mm a")
        return timeFormatter.format(date)
    }
}