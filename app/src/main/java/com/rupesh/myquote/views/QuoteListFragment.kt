package com.rupesh.myquote.views

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.rupesh.myquote.QuoteApplication
import com.rupesh.myquote.R
import com.rupesh.myquote.adapters.QuoteListAdapter
import com.rupesh.myquote.databinding.FragmentQuoteListBinding
import com.rupesh.myquote.utils.RecyclerViewItemDecorator
import com.rupesh.myquote.viewModel.QuoteListViewModel
import com.rupesh.myquote.viewModel.QuoteViewModelFactory
import com.rupesh.myquote.viewModel.QuoteViewmodel

class QuoteListFragment : Fragment() {

    private lateinit var viewModel: QuoteViewmodel
    lateinit var binding: FragmentQuoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val repo = (requireActivity().application as QuoteApplication).quoteReposetory
        viewModel = ViewModelProvider(this, QuoteViewModelFactory(requireContext(),repo)).get(QuoteViewmodel::class.java)
        binding.quoteListRecycleView.addItemDecoration(RecyclerViewItemDecorator(20, 2, 2, 2))
        binding.quoteListRecycleView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)
       val adapter = QuoteListAdapter(requireContext())
        binding.quoteListRecycleView.adapter = adapter
        viewModel.allQuote.observe(viewLifecycleOwner){
        adapter.submitList(it)
        }
        viewModel.getAllQuote()
    }

}