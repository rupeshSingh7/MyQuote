package com.rupesh.myquote.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.rupesh.myquote.QuoteApplication
import com.rupesh.myquote.R
import com.rupesh.myquote.viewModel.QuoteViewModelFactory
import com.rupesh.myquote.viewModel.QuoteViewmodel

class RandomQuoteFragment : Fragment() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_random_quote, container, false)
        initView(rootView)

        return rootView
    }
    fun initView(root: View){
        textView = root.findViewById(R.id.txtquote)
    }

    override fun onResume() {
        super.onResume()
        val quoteRepository = (requireActivity().application as QuoteApplication).quoteReposetory
        val quoteViewmodel = ViewModelProvider(this, QuoteViewModelFactory(requireContext(), quoteRepository!!)).get(QuoteViewmodel::class.java)
        quoteViewmodel.randomQuote.observe(this){
            textView!!.text = "\" ${it.content} \" \nBy - ${it.author}"
        }
        quoteViewmodel.getRanDomQuote()
    }

}