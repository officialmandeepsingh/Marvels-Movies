package com.mandeep.marvelbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.marvelbook.data.model.Movie
import com.mandeep.marvelbook.databinding.ItemUpcomingMoviesBinding

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.ui.adapter
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 07 Apr, 2023
 *
 **/
class UpComingMovieAdapter : PagingDataAdapter<Movie, UpComingMovieAdapter.UpComingViewHolder>(UserDiffUtil) {

    inner class UpComingViewHolder(val binding: ItemUpcomingMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie?) {
            binding.apply {
                tvMoviewName.text = movie?.originalTitle
                tvMoviewGenre.text = movie?.originalLanguage
            }

        }
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder =
        UpComingViewHolder(
            ItemUpcomingMoviesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    object UserDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }

    }
}