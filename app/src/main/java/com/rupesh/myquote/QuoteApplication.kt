package com.rupesh.myquote

import android.app.Application
import com.rupesh.myquote.ApiServices.QuoteApiService
import com.rupesh.myquote.ApiServices.RetroFitHelper
import com.rupesh.myquote.db.MyQuoteDb
import com.rupesh.myquote.repository.QuoteReposetory

class QuoteApplication: Application() {
    var quoteReposetory: QuoteReposetory? = null
    override fun onCreate() {
        super.onCreate()
        val apiService: QuoteApiService = RetroFitHelper.getInstance().create(QuoteApiService::class.java)

        val db = MyQuoteDb.getInstance(applicationContext)
        quoteReposetory = QuoteReposetory(applicationContext,
            quoteApiService = apiService,
            myQuoteDb = db)
    }
}