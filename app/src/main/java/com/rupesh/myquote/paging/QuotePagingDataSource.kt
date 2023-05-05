package com.rupesh.myquote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rupesh.myquote.ApiServices.QuoteApiService
import com.rupesh.myquote.db.commonModule.Quote
import java.lang.Exception

const val STARTING_PAGE_INDEX = 1

class QuotePagingDataSource(private val quotesAPI: QuoteApiService) : PagingSource<Int, Quote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = quotesAPI.getQuotes(position)

            LoadResult.Page(
                data = response.results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

