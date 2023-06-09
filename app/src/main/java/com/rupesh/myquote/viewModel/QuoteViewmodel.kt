package com.rupesh.myquote.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rupesh.myquote.db.commonModule.Quote
import com.rupesh.myquote.repository.QuoteReposetory
import com.rupesh.myquote.repository.ResponseCallType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuoteViewmodel(private val context: Context ,private val quoteReposetory: QuoteReposetory): ViewModel() {

    val randomQuote: LiveData<Quote>
    get() = quoteReposetory.randomQuote

    var status = -1


     fun getRanDomQuote(){
         viewModelScope.launch(Dispatchers.IO){
            status = quoteReposetory.getRandomQuote()
             withContext(Dispatchers.Main){
                 //update UI
                 if (status ==  ResponseCallType.ONLINE.value){
                     Toast.makeText(context, "online", Toast.LENGTH_SHORT).show()
                 }else if (status ==  ResponseCallType.OFFLINE.value) {
                     Toast.makeText(context, "OffLine", Toast.LENGTH_SHORT).show()
                 }else {
                     Toast.makeText(context, "${ResponseCallType.valueOf(status.toString())}", Toast.LENGTH_SHORT).show()
                 }
             }
         }
    }

    val allQuote: LiveData<List<Quote>>
        get() = quoteReposetory.allQuotesList
    fun getAllQuote(){
        viewModelScope.launch {
            val status = quoteReposetory.getAllQuotes()
            withContext(Dispatchers.Main){
                //update UI
                if (status ==  ResponseCallType.ONLINE.value){
                    Toast.makeText(context, "online", Toast.LENGTH_SHORT).show()
                }else if (status ==  ResponseCallType.OFFLINE.value) {
                    Toast.makeText(context, "OffLine", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(context, "${ResponseCallType.valueOf(status.toString())}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}