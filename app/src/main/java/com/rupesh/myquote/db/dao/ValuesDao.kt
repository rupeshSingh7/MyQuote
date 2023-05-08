package com.rupesh.myquote.db.dao

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.*
import com.rupesh.myquote.db.commonModule.Values

@Dao
interface ValuesDao {
    @NonNull
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: Values) : Int

    @Delete
    suspend fun delete(values: Values): Int

    @Query("SELECT * FROM valueItem")
    suspend fun getAllValues(): List<Values>


}