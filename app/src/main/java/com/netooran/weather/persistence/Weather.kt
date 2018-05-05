package com.netooran.weather.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weather")
class Weather(@PrimaryKey(autoGenerate = true)
              val id: Long? = null,
              @ColumnInfo(name = "location_name")
              val location: String,
              val temperature: String,
              @ColumnInfo(name = "location_text")
              val weatherText: String,
              @ColumnInfo(name = "icon_id")
              val iconId: Int)