package com.rupesh.myquote.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.rupesh.myquote.QuoteApplication
import com.rupesh.myquote.R
import com.rupesh.myquote.adapters.GridAdapter.*
import com.rupesh.myquote.adapters.GridAdapter
import com.rupesh.myquote.callBackInterface.AdapterOnClickCallBack
import com.rupesh.myquote.databinding.FragmentMainListBinding
import com.rupesh.myquote.utils.RecyclerViewItemDecorator
import com.rupesh.myquote.utils.Utils
import com.rupesh.myquote.viewModel.QuoteViewModelFactory
import com.rupesh.myquote.viewModel.QuoteViewmodel
import com.rupesh.myquote.viewModel.ValueListViewModel

class MainListFragment : Fragment(), AdapterOnClickCallBack {
    private var _binding: FragmentMainListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    var adapter: GridAdapter? = null
    var navHostFragment: NavHostFragment? = null
    var viewmodel: ValueListViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      // return inflater.inflate(R.layout.fragment_main_list, container, false)
       _binding = FragmentMainListBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
       // recyclerView = rootView.findViewById(R.id.recycleView)
        val noOfColumns = Utils.calculateNoOfColumns(requireContext())
        binding.recycleView.addItemDecoration(RecyclerViewItemDecorator(2, 2, 2, 2))

        binding.recycleView.layoutManager = GridLayoutManager(activity, noOfColumns)

        adapter = GridAdapter(requireActivity(), this)
        binding.recycleView.adapter = adapter
        navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        //val navController = navHostFragment?.navController
        val quoteRepository = (requireActivity().application as QuoteApplication).quoteReposetory
        viewmodel = ViewModelProvider(this, QuoteViewModelFactory(requireContext(), quoteRepository, 1)).get(ValueListViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        viewmodel?.getAllValues()
        viewmodel?.allValues?.observe(this){
//            it.forEach { item ->
                adapter?.submitList(it)
//            }

        }


    }
    override fun onClickCallBack(i: Int, item: String) {
        when(item){
            "Random Quote" -> {
                navHostFragment?.navController?.navigate(MainListFragmentDirections.actionMainListFragmentToRandomQuoteFragment2())
            }
            "Quote List" -> {
                navHostFragment?.navController?.navigate(MainListFragmentDirections.actionMainListFragmentToQuoteListFragment())
            }
            "S List" -> {
                navHostFragment?.navController?.navigate(MainListFragmentDirections.actionMainListFragmentToSurveyActivity())
            }
            "I List" -> {
                navHostFragment?.navController?.navigate(MainListFragmentDirections.actionMainListFragmentToInventoryActivity())
            }
        }
    }

}