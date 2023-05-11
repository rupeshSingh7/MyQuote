package com.rupesh.myquote.db.dao

import androidx.room.*
import com.rupesh.myquote.db.commonModule.Quote
@Dao
interface QuoteDao {

    @Insert
    suspend fun insertQoute(vararg : Quote)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateQoute(vararg : Quote)

    @Insert
    suspend fun insertQuotes(quote: List<Quote>)

    @Delete
    suspend fun deleteQuote(quote: Quote)

    @Query("DELETE FROM Quote WHERE quoteId = :id")
    suspend fun deleteQuoteById(id: Long)

    @Query("SELECT * FROM Quote")
    suspend fun getAllQuotes(): List<Quote>

    @Query("SELECT * FROM Quote where quoteId = :any")
    suspend fun getRandomQuote2(any: Long): Quote?

    @Query("SELECT * FROM Quote ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomQuote(): Quote?

    @Update
    suspend fun updateQuote(quote: Quote)
}