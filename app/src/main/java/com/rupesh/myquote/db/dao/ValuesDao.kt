package com.rupesh.myquote.db.dao

import androidx.annotation.NonNull
import androidx.room.*
import com.rupesh.myquote.db.commonModule.Values

@Dao
interface ValuesDao {

    @Insert
    suspend fun insert(value: Values)

    @Delete
    suspend fun delete(values: Values)

    @Query("SELECT * FROM valueItem")
    suspend fun getAllValues(): List<Values>


}