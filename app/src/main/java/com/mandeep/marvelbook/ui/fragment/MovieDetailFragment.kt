package com.mandeep.marvelbook.ui.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.mandeep.marvelbook.data.base.Resource
import com.mandeep.marvelbook.data.model.Movie
import com.mandeep.marvelbook.databinding.FragmentMovieDetailBinding
import com.mandeep.marvelbook.ui.viewModel.AppViewModel
import com.mandeep.marvelbook.util.base.BaseFragment
import com.mandeep.marvelbook.util.extension.LoadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {
    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: AppViewModel by viewModels()

    override fun callAPI() {
        getMovieDetails(args.movieId)
    }

    override fun bindInitViews() {

    }

    override fun bindInitAdapters() {

    }

    override fun bindListeners() {

    }

    override fun bindObservers() {

    }

    override fun initViewModels() {

    }

    private  fun getMovieDetails(movieId: String){
        lifecycleScope.launch {
            viewModel.getMovieDetails(movieId).collectLatest {
                when(it){
                    is Resource.Succes -> {
                        setMovieDetails(it.data)
                    }
                    is Resource.Error -> {
                        Log.d(TAG, "getMovieDetails() called with error ${it.error}")
                    }
                    is Resource.Loading -> Log.d(TAG, "getMovieDetails() called")
                    is Resource.UnAuthorized -> Log.d(TAG, "getMovieDetails() called")
                }
            }
        }
    }

    private fun setMovieDetails(movie: Movie?) {
        binding.apply {
            movie.let {
                tvMovieName.text = it?.originalTitle?:"Movie Name"
                tvDescription.text = it?.overview
            }
            movie?.posterPath?.let { ivImageView.LoadImage(it) }

        }
    }
}