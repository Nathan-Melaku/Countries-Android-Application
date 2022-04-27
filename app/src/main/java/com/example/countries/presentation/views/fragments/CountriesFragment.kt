package com.example.countries.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.countries.R
import com.example.countries.adaptors.CountriesAdapter
import com.example.countries.data.network.response.Resource
import com.example.countries.databinding.FragmentCountriesBinding
import com.example.countries.viewModels.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CountriesFragment : Fragment() {

    private lateinit var countriesAdapter: CountriesAdapter

    private val viewModel: CountriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentCountriesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)

        countriesAdapter = CountriesAdapter(CountriesAdapter.OnClickListener { country ->
            Timber.d(country.name.commonName + " is clicked!")
            viewModel.selectCountry(country)
            // navigate to DetailFragment
            val action = CountriesFragmentDirections.actionCountriesFragmentToDetailFragment()
            binding.root.findNavController().navigate(action)

        })

        viewModel.countries.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Timber.d("Loading")
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    Timber.d(it.message)
                    binding.error.text = it.message
                    binding.error.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.countriesList.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("Loaded ${it.data?.size} Countries.")
                    countriesAdapter.submitList(it.data)
                    binding.error.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.countriesList.visibility = View.VISIBLE
                }
            }
        }

        val recyclerView = binding.countriesList
        recyclerView.adapter = countriesAdapter
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.setHasFixedSize(true)

        return binding.root
    }
}