package com.mandeep.marvelbook.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mandeep.marvelbook.R
import com.mandeep.marvelbook.databinding.FragmentHomeBinding
import com.mandeep.marvelbook.ui.adapter.MoviesAdapter
import com.mandeep.marvelbook.ui.viewModel.AppViewModel
import com.mandeep.marvelbook.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: AppViewModel by viewModels()
    private lateinit var popularMovieAdapter: MoviesAdapter
    private lateinit var nowPlayingMovieAdapter: MoviesAdapter
    private lateinit var topRatedMovieAdapter: MoviesAdapter
    private lateinit var upComingMovieAdapter: MoviesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun callAPI() {

    }

    override fun bindInitViews() {
        bindPopularMoviesView()
        bindNowPlayingMoviesView()
        bindTopRatedMoviesView()
        bindUpComingMoviesView()
    }

    private fun bindUpComingMoviesView() {
        binding.layUpComingMovies.apply {
            tvTitle.text = getString(R.string.upcoming_movies)
            tvSeeMore.text = getString(R.string.view_all)
        }
    }

    private fun bindTopRatedMoviesView() {
        binding.layTopRatedMovies.apply {
            tvTitle.text = getString(R.string.top_rated_movies)
            tvSeeMore.text = getString(R.string.view_all)
        }
    }

    private fun bindPopularMoviesView() {
        binding.layPopularMovies.apply {
            tvTitle.text = getString(R.string.popular_movies)
            tvSeeMore.text = getString(R.string.view_all)
        }
    }

    private fun bindNowPlayingMoviesView() {
        binding.layNowPlayingMovies.apply {
            tvTitle.text = getString(R.string.now_playing_movies)
            tvSeeMore.text = getString(R.string.view_all)
        }
    }

    override fun bindInitAdapters() {
        initializePopularAdapter()
        initializeNowPlayingAdapter()
        initializeTopRatedAdapter()
        initializeUpComingAdapter()
    }

    private fun initializeUpComingAdapter() {
        upComingMovieAdapter = MoviesAdapter()
        binding.layUpComingMovies.recyclerView.adapter = upComingMovieAdapter
    }

    private fun initializeTopRatedAdapter() {
        topRatedMovieAdapter = MoviesAdapter()
        binding.layTopRatedMovies.recyclerView.adapter = topRatedMovieAdapter
    }

    private fun initializeNowPlayingAdapter() {
        nowPlayingMovieAdapter = MoviesAdapter()
        binding.layNowPlayingMovies.recyclerView.adapter = nowPlayingMovieAdapter
    }

    private fun initializePopularAdapter() {
        popularMovieAdapter = MoviesAdapter()
        binding.layPopularMovies.recyclerView.adapter = popularMovieAdapter
    }

    override fun bindListeners() {

    }

    override fun bindObservers() {
        lifecycleScope.launch {
            viewModel.getPopularMovies().collectLatest { popularMovieAdapter.submitData(it) }
        }

        lifecycleScope.launch {
            viewModel.getNowPlayingMovies().collectLatest { nowPlayingMovieAdapter.submitData(it) }
        }

        lifecycleScope.launch {
            viewModel.getTopRatedMovies().collectLatest { topRatedMovieAdapter.submitData(it) }
        }

        lifecycleScope.launch {
            viewModel.getUpComingMovies().collectLatest { upComingMovieAdapter.submitData(it) }
        }
    }

    override fun initViewModels() {

    }

}