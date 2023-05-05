package com.rupesh.myquote.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rupesh.myquote.repository.QuoteReposetory

class QuoteViewModelFactory(private val context: Context, private val quoteReposetory: QuoteReposetory): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuoteViewmodel(context, quoteReposetory) as T
    }
}