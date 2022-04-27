package com.example.countries.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.R
import com.example.countries.adaptors.SearchAdapter
import com.example.countries.databinding.FragmentSearchBinding
import com.example.countries.viewModels.CountriesViewModel
import com.example.countries.viewModels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by activityViewModels()
    private val countriesViewModel: CountriesViewModel by activityViewModels()

    private lateinit var searchAdapter: SearchAdapter

    private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentSearchBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_search, container, false)

        searchAdapter = SearchAdapter(SearchAdapter.OnClickListener { country ->
            Timber.d("$country")
            countriesViewModel.selectCountry(country)
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment()
            binding.root.findNavController().navigate(action)
        })

        val searchTextView = binding.editTextSearch
        searchTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Timber.d(p0.toString())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length: Int = p0?.length ?: 0
                if (length >= 3) {
                    job = lifecycleScope.launch {
                        searchViewModel.search(p0.toString()).collect { listOfCountry ->
                            Timber.d("${listOfCountry.size} Countries Loaded!")
                            searchAdapter.submitList(listOfCountry)
                        }
                    }
                } else {
                    searchAdapter.submitList(listOf())
                    job?.cancel()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Timber.d(p0.toString())
            }
        })

        val recyclerView = binding.searchList
        recyclerView.adapter = searchAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        return binding.root
    }
}