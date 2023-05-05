package com.rupesh.myquote.db.jsonobject

import com.rupesh.myquote.db.commonModule.Quote

data class JsonQuote(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Quote>,
    val totalCount: Int,
    val totalPages: Int
)
