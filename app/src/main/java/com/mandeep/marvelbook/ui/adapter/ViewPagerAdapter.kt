package com.mandeep.marvelbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.marvelbook.data.model.Movie
import com.mandeep.marvelbook.databinding.ItemMovieBannerBinding
import com.mandeep.marvelbook.util.extension.LoadImage

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.ui.adapter
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Sat 08 Apr, 2023
 *
 **/
class ViewPagerAdapter() :
    PagingDataAdapter<Movie, ViewPagerAdapter.MoviesViewHolder>(MoviesAdapter.UserDiffUtil) {
    inner class MoviesViewHolder(private val binding: ItemMovieBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                movie.posterPath?.let { item.LoadImage(it) }
            }
        }
    }

    object UserDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie,
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MoviesViewHolder =
        MoviesViewHolder(
            ItemMovieBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

}