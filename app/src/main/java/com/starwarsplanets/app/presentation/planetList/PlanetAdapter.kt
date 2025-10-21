package com.starwarsplanets.app.presentation.planetList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.starwarsplanets.app.databinding.ItemPlanetBinding
import com.starwarsplanets.app.domain.model.PlanetDto

class PlanetAdapter(private val onItemClick: (PlanetDto) -> Unit) : PagingDataAdapter<PlanetDto, PlanetAdapter.PlanetViewHolder>(PlanetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val binding = ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planet = getItem(position)
        if (planet != null) {
            holder.bind(planet)
            holder.itemView.setOnClickListener { onItemClick(planet) }
        }
    }

    class PlanetViewHolder(private val binding: ItemPlanetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(planet: PlanetDto) {
            binding.tvPlanetName.text = planet.name
            binding.tvPlanetClimate.text = planet.climate
            binding.planetThumbnail.load(planet.thumbnail) {
                crossfade(true)
                transformations(RoundedCornersTransformation(100f))
            }
        }
    }

    class PlanetDiffCallback : DiffUtil.ItemCallback<PlanetDto>() {
        override fun areItemsTheSame(oldItem: PlanetDto, newItem: PlanetDto): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: PlanetDto, newItem: PlanetDto): Boolean {
            return oldItem == newItem
        }
    }
}