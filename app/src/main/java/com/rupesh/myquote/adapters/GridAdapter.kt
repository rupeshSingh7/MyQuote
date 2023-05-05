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
import com.rupesh.myquote.callBackInterface.AdapterOnClickCallBack

class GridAdapter(private val mContext: Context, private val onClickCallBack: AdapterOnClickCallBack) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {



    //added by vinod
    private val mDiffer: AsyncListDiffer<Values> = AsyncListDiffer(this, object: DiffUtil.ItemCallback<Values>() {


        override fun areItemsTheSame(oldItem: Values, newItem: Values): Boolean {

            return oldItem.summaryType == newItem.summaryType
        }


        override fun areContentsTheSame(oldItem: Values, newItem: Values): Boolean {

            return oldItem.equals(newItem)

        }

    })



    fun submitList(values: List<Values>){

        mDiffer.submitList(values)

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.main_item_list, parent, false)

        return ViewHolder(view)

    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.imageView.setImageResource(mDiffer.currentList[position].imageId)

        holder.textView.text = mDiffer.currentList[position].summaryType

        holder.itemView.setOnClickListener { onClickCallBack.onClickCallBack(0, mDiffer.currentList[position].summaryType) }
    }


//    override fun getItemId(position: Int): Long {
//
//        return mDiffer.currentList[position].constant.toLong()
//
//    }



    override fun getItemCount(): Int {

        return mDiffer.currentList.size
    }


    fun getItem(position: Int): Values {

        return mDiffer.currentList[position]

    }





    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        var imageView: ImageView

        var textView: TextView



        init {
            textView = itemView.findViewById(R.id.tvName)
            imageView = itemView.findViewById(R.id.imvIcon)

        }

    }



    class Values {

        var imageId: Int = 0
            private set
        var summaryType: String = ""




        constructor(imageId: Int, summaryType: String) {

            this.imageId = imageId

            this.summaryType = summaryType

        }

    }

}