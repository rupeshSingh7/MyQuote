package com.rupesh.myquote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.rupesh.myquote.ApiServices.QuoteApiService
import com.rupesh.myquote.db.MyQuoteDb
import javax.inject.Inject

@ExperimentalPagingApi
class QuoteRepository @Inject constructor(
    private val quotesAPI: QuoteApiService,
    private val quoteDatabase: MyQuoteDb
) {

    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {
            QuotePagingDataSource(quotesAPI)
        }
    ).liveData

}