package com.example.moviesearch.presentation.mappers

import java.text.SimpleDateFormat
import java.util.Locale

object DateTimeFormatter {


    const val ISO_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    fun mapToDayMonthYear(date: String): String {
        return SimpleDateFormat(ISO_DATE_PATTERN, Locale.getDefault()).format(date)
    }
}

