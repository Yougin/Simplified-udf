package com.blinkslabs.blinkist.android.challenge.data.book.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import org.threeten.bp.LocalDate

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false) var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "publish_date") var publishDate: LocalDate,
    @ColumnInfo(name = "cover_image_url") var coverImageUrl: String
)

object BooksTypeConverters {

  @TypeConverter
  @JvmStatic
  fun toOffsetDateTime(value: Long?): LocalDate? {
    return if (value != null) LocalDate.ofEpochDay(value) else null
  }

  @TypeConverter
  @JvmStatic
  fun fromOffsetDateTime(date: LocalDate?): Long? {
    return date?.toEpochDay()

  }
}