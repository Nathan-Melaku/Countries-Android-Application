package com.example.countries.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.countries.R
import com.example.countries.databinding.FragmentDetailBinding
import com.example.countries.viewModels.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel:CountriesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentDetailBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_detail, container, false)

        viewModel.selectedCountry.observe(viewLifecycleOwner) {
            Timber.d("$it")
            binding.country = it
            Glide.with(this)
                .load(it.flag.png)
                .into(binding.imageView)
        }

        return binding.root
    }
}