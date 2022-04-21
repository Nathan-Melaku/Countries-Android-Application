package com.example.countries.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.data.model.detabaseModel.CountryDb
import com.example.countries.databinding.CountryViewBinding

class CountriesAdapter (private val onClickListener: OnClickListener) :
    ListAdapter<CountryDb, CountriesAdapter.CountryViewHolder>(CountryDiffCallback) {

    private lateinit var binding: CountryViewBinding

    class CountryViewHolder(private val binding: CountryViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryDb) {
            binding.country = country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        binding = CountryViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holderCountry: CountryViewHolder, position: Int) {
        val country = getItem(position)
        holderCountry.itemView.setOnClickListener{
            onClickListener.onClick(country)
        }
        holderCountry.bind(country)
    }

    object CountryDiffCallback: DiffUtil.ItemCallback<CountryDb>(){
        override fun areItemsTheSame(oldItem: CountryDb, newItem: CountryDb): Boolean {
            return newItem == oldItem
        }

        override fun areContentsTheSame(oldItem: CountryDb, newItem: CountryDb): Boolean {
            return newItem.name.commonName == oldItem.name.commonName
        }

    }

    class OnClickListener (val clickListener: (country: CountryDb) -> Unit) {
        fun onClick(country: CountryDb) = clickListener(country)
    }
}