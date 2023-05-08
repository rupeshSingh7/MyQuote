package com.rupesh.myquote.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rupesh.myquote.db.commonModule.Quote
import com.rupesh.myquote.db.commonModule.Values
import com.rupesh.myquote.db.dao.QuoteDao
import com.rupesh.myquote.db.dao.ValuesDao

@Database(entities = [Quote::class, Values::class], version = 1)
abstract class MyQuoteDb : RoomDatabase() {
    abstract fun getQuoteDao(): QuoteDao
    abstract fun getValuesDao(): ValuesDao

    companion object{
       private var INSTANCE: MyQuoteDb? = null
        fun getInstance(context: Context): MyQuoteDb{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MyQuoteDb::class.java,
                        "testdb"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}