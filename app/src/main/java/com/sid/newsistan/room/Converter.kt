package com.sid.newsistan.room

import androidx.room.TypeConverter
import com.fasterxml.jackson.databind.ObjectMapper
import com.sid.newsistan.dataClass.Source

object Converters {

    @TypeConverter
    fun fromObjectToJSonString(value: Source?): String {
        val mapper = ObjectMapper()
        return mapper.writeValueAsString(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: String?): Source? {
        val mapper = ObjectMapper()
        return mapper.readValue(date, Source::class.java)

    }
}