package com.rupesh.myquote.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rupesh.myquote.db.commonModule.Values
import com.rupesh.myquote.repository.QuoteReposetory
import kotlinx.coroutines.launch
import com.rupesh.myquote.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ValueListViewModel(private val context: Context, private val quoteRepo: QuoteReposetory) : ViewModel() {

    val allValues: LiveData<List<Values>>
    get() = quoteRepo.allValues

    fun getAllValues(){
        viewModelScope.launch {
            quoteRepo.getValues()
        }
    }

    fun deleteValue(values: Values, showMsg: Boolean = false){
        viewModelScope.launch(Dispatchers.IO) {
            val isDel = quoteRepo.deleteValues(values)
           val msg =  if (isDel > 0){
                quoteRepo.getValues() //update value
              R.string.deleted

            }else {
               R.string.unable_to_delete
            }

            if (showMsg){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun insertValue(values: Values, showMsg: Boolean = false){
        viewModelScope.launch(Dispatchers.IO) {
            val isDel = quoteRepo.insertValues(values)
           val msg =  if (isDel > 0){
                quoteRepo.getValues() //update value
              R.string.added

            }else {
               R.string.faield
            }

            if (showMsg){
                withContext(Dispatchers.Main){
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

}