package com.rupesh.myquote.db.commonModule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quote")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val quoteId: Long,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
//    val tags: List<String>
)
