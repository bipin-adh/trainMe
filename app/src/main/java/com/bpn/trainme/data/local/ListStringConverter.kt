package com.bpn.trainme.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

object ListStringConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return json.encodeToString(list)
    }

    @TypeConverter
    fun toList(jsonString: String): List<String> {
        return json.decodeFromString(jsonString)
    }
}