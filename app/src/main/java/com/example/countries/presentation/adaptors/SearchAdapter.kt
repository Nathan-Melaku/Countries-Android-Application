package com.example.countries.adaptors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.data.database.dto.CountryDb
import com.example.countries.databinding.CountryViewBinding

class SearchAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<CountryDb, SearchAdapter.SearchViewHolder>(SearchDiffCallback) {

    private lateinit var binding: CountryViewBinding

    class SearchViewHolder(private val binding: CountryViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryDb) {
            binding.country = country
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding = CountryViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val country = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(country)
        }
        holder.bind(country)
    }

    object SearchDiffCallback : DiffUtil.ItemCallback<CountryDb>() {
        override fun areItemsTheSame(oldItem: CountryDb, newItem: CountryDb): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CountryDb, newItem: CountryDb): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class OnClickListener(val clickListener: (country: CountryDb) -> Unit) {
        fun onClick(country: CountryDb) = clickListener(country)
    }
}