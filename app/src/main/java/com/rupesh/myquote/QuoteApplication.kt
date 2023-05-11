package com.rupesh.myquote

import android.app.Application
import android.widget.Toast
import com.rupesh.myquote.ApiServices.QuoteApiService
import com.rupesh.myquote.ApiServices.RetroFitHelper
import com.rupesh.myquote.adapters.GridAdapter
import com.rupesh.myquote.db.MyQuoteDb
import com.rupesh.myquote.db.commonModule.Values
import com.rupesh.myquote.repository.QuoteReposetory
import kotlinx.coroutines.*

class QuoteApplication: Application() {
    private var job = Job()
    private val applicationScope = CoroutineScope(Dispatchers.Main + job)
    private val list = listOf(
        Values("Random Quote", 1 ),
        Values("Quote List", 2),
        Values("S List",3),
        Values("I List", 4)
    ).apply {
        this.forEach {
            it.imageId = R.drawable.ic_launcher_foreground
        }
    }

    lateinit var quoteReposetory: QuoteReposetory
    lateinit var db: MyQuoteDb
    override fun onCreate() {
        super.onCreate()
        val apiService: QuoteApiService = RetroFitHelper.getInstance().create(QuoteApiService::class.java)

        db = MyQuoteDb.getInstance(applicationContext)
        quoteReposetory = QuoteReposetory(applicationContext,
            quoteApiService = apiService,
            myQuoteDb = db)
        init()
    }

    private fun init() {
        applicationScope.launch {
            val value = db.getValuesDao().getAllValues()
            if (value.isEmpty()){
                var i = 1
                list.forEach {
                    i++
                    it.id = i.toLong() + Math.random().toLong() * 100
                    db.getValuesDao().insert(it)
                 }
             }
        }

    }
}