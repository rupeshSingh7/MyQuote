package com.rupesh.myquote.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rupesh.myquote.ApiServices.QuoteApiService
import com.rupesh.myquote.db.MyQuoteDb
import com.rupesh.myquote.db.commonModule.Quote
import com.rupesh.myquote.db.commonModule.Values
import com.rupesh.myquote.utils.NetworkUtils
import java.lang.Exception

class QuoteReposetory(private val context: Context, private val quoteApiService: QuoteApiService, private val myQuoteDb: MyQuoteDb) {

    /**
     * Repo handle for Value items
     */
    private val arrValues = MutableLiveData<List<Values>>()
    val allValues = arrValues

    suspend fun getValues(){
        allValues.value = myQuoteDb.getValuesDao().getAllValues()
    }

    suspend fun insertValues(values: Values): Int{
       return myQuoteDb.getValuesDao().insert(values) //insert to db
    }
    suspend fun deleteValues(values: Values): Int{
        return myQuoteDb.getValuesDao().delete(values)
    }

    /**
     * Repo handle for Random Quotation
     */

    private val quote = MutableLiveData<Quote>()

    val randomQuote: LiveData<Quote>
    get() = quote

    suspend fun getRandomQuote(): Int{
        if (NetworkUtils.isConnectedToInterNet(context)){
            try {
                val result = quoteApiService.getRandomQuote()
                val newQuote = result.body()
                if (result.isSuccessful && newQuote != null){
                    myQuoteDb.getQuoteDao().insertOrUpdateQoute(newQuote)
                    quote.postValue(newQuote!!)
                    return ResponseCallType.ONLINE.value
                }else {
                    return ResponseCallType.RESPONSE_NULL_EMPTY.value
                }
            }catch (e: Exception){
                return ResponseCallType.RESPONSE_CALL_ERROR.value
            }

        }else{
            //offline get from db
            val newQuote = myQuoteDb.getQuoteDao().getRandomQuote()
            if (newQuote != null) {
                quote.postValue(newQuote!!)
                return ResponseCallType.OFFLINE.value
            }else{
                return ResponseCallType.OFFLINE_NOT_FOUND_IN_DB.value
            }
        }
        return ResponseCallType.None.value
    }


}
enum class ResponseCallType(val value: Int){
    ONLINE(0),
    OFFLINE(1),
    OFFLINE_NOT_FOUND_IN_DB(5),
    RESPONSE_NULL_EMPTY(2),
    RESPONSE_CALL_ERROR(3),
    None(3)

}