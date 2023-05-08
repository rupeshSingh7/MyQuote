package com.rupesh.myquote.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rupesh.myquote.repository.QuoteReposetory

class QuoteViewModelFactory(private val context: Context, private val quoteReposetory: QuoteReposetory,private val type: Int = 0): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (type == 1)
            ValueListViewModel(context, quoteReposetory) as T
         else  QuoteViewmodel(context, quoteReposetory) as T
    }
}