package com.rupesh.myquote.db.commonModule

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rupesh.myquote.R

@Entity(tableName = "valueItem")
data class Values(val summaryType: String = "", val type: Int){

    @PrimaryKey(autoGenerate = false)
    var id: Long = 0

    var imageId: Int = 0

}
