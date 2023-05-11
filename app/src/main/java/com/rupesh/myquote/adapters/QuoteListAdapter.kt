package com.rupesh.myquote.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rupesh.myquote.R
import com.rupesh.myquote.db.commonModule.Quote

class QuoteListAdapter(val context: Context): RecyclerView.Adapter<QuoteListAdapter.ViewHolder>() {
   private val diffUtil: AsyncListDiffer<Quote> = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Quote>(){
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
           return oldItem == newItem
        }

    })

    fun submitList(values: List<Quote>){
        diffUtil.submitList(values)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = QuoteListAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.quote_list_item,parent,false))
        return view
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView: TextView
        init {
            textView = itemView.findViewById(R.id.tvquote)
        }

    }
    override fun getItemCount() = diffUtil.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = diffUtil.currentList[position]
        holder.textView.text = "${quote.content} \nBy-${quote.author}"
    }
}