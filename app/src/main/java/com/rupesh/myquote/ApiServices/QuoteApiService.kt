package com.rupesh.myquote.ApiServices

import com.rupesh.myquote.db.commonModule.Quote
import com.rupesh.myquote.db.jsonobject.JsonQuote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApiService {
    @GET("/quotes")
    suspend fun getDefaultQuote(): Response<List<JsonQuote>>

    @GET("/quotes")
    suspend fun getQuoteListByPageNo(@Query("page") page: Int): Response<JsonQuote>
  //this is using in dagger hill with paggination
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): JsonQuote
//end
    @GET("/random")
    suspend fun getRandomQuote(): Response<Quote>

}