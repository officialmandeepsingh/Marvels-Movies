package com.mandeep.marvelbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mandeep.marvelbook.data.model.Movie
import com.mandeep.marvelbook.databinding.ItemMoviesBinding
import com.mandeep.marvelbook.util.extension.LoadImage
import com.mandeep.marvelbook.util.extension.gone
import com.mandeep.marvelbook.util.extension.onClick
import com.mandeep.marvelbook.util.extension.visible

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.ui.adapter
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 07 Apr, 2023
 *
 **/
class MoviesAdapter(private val clickOnMovie: (String) -> Unit) : PagingDataAdapter<Movie, MoviesAdapter.MoviesViewHolder>(UserDiffUtil) {


    inner class MoviesViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                movie.posterPath?.let { item.LoadImage(it) }
                root onClick {clickOnMovie(movie.id.toString())}
                /*when(movie.adult){
                    true -> ivIsAdult.visible()
                    false -> ivIsAdult.gone()
                    else -> ivIsAdult.gone()
                }*/

            }
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)

        movie?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

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
}