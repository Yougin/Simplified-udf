package com.blinkslabs.blinkist.android.challenge.data.book.entity

import androidx.room.*
import java.io.Serializable


@Entity
class BookEntity(
    @PrimaryKey(autoGenerate = false) var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "author") var author: String,
//    @ColumnInfo(name = "publish_date") var publishDate: LocalDate,
    @ColumnInfo(name = "cover_image_url") var coverImageUrl: String
) : Serializable
