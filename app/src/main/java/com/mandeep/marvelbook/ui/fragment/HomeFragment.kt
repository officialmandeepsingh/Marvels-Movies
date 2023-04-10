package com.mandeep.marvelbook.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.mandeep.marvelbook.R
import com.mandeep.marvelbook.data.base.Resource
import com.mandeep.marvelbook.databinding.FragmentHomeBinding
import com.mandeep.marvelbook.ui.adapter.MoviesAdapter
import com.mandeep.marvelbook.ui.adapter.ViewPagerAdapter
import com.mandeep.marvelbook.ui.viewModel.AppViewModel
import com.mandeep.marvelbook.util.base.BaseFragment
import com.mandeep.marvelbook.util.extension.gone
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Math.abs

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: AppViewModel by viewModels()
    private lateinit var popularMovieAdapter: MoviesAdapter

    //    private lateinit var popularMovieAdapter: ViewPagerAdapter
    private lateinit var nowPlayingMovieAdapter: MoviesAdapter
    private lateinit var topRatedMovieAdapter: ViewPagerAdapter
    private lateinit var upComingMovieAdapter: MoviesAdapter
    private lateinit var mainHandler: Handler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun callAPI() {

    }

    override fun bindInitViews() {
        mainHandler = Handler(Looper.getMainLooper())
        bindPopularMoviesView()
        bindNowPlayingMoviesView()
        bindTopRatedMoviesView()
        bindUpComingMoviesView()
    }

    private fun bindViewPagerMovies() {
//        popularMovieAdapter = ViewPagerAdapter()

    }

    private fun addCompositePageTransform(): CompositePageTransformer {
        val pageTransformer = CompositePageTransformer()
        pageTransformer.addTransformer(MarginPageTransformer(45))
        pageTransformer.addTransformer { page, position ->
            val pagePosition = 1 - abs(position)
            val cal = 0.85f + pagePosition * 0.15f
            page.scaleY = cal
        }
        return pageTransformer
    }

    private fun bindUpComingMoviesView() {
        binding.layUpComingMovies.apply {
            tvTitle.text = getString(R.string.upcoming_movies)
            tvSeeMore.text = getString(R.string.view_all)
        }
    }

    private fun bindTopRatedMoviesView() {
        binding.layTopRatedMovies.apply {
            tvTitle.gone()
            tvSeeMore.gone()
            viewPager.apply {
                (getChildAt(0) as RecyclerView).clearOnChildAttachStateChangeListeners()
                adapter = topRatedMovieAdapter
                clipToPadding = false
                clipChildren = false
                offscreenPageLimit = 3
                setPageTransformer(addCompositePageTransform())
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                registerOnPageChangeCallback(object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        if (mainHandler != null) {
                            mainHandler.removeCallbacks(autoScroll)
                            mainHandler.postDelayed(autoScroll, 1500)
                        }
                    }
                }
                )
            }
        }

    }

    val autoScroll = Runnable {
        var currentPosition = binding.layTopRatedMovies.viewPager.currentItem
        currentPosition += 1
        if (currentPosition == topRatedMovieAdapter.itemCount)
            currentPosition = 0
        binding.layTopRatedMovies.viewPager.setCurrentItem(currentPosition, true)

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
        upComingMovieAdapter = MoviesAdapter(::onItemClicked)
        binding.layUpComingMovies.recyclerView.adapter = upComingMovieAdapter
    }

    private fun initializeTopRatedAdapter() {
        topRatedMovieAdapter = ViewPagerAdapter()

    }

    private fun initializeNowPlayingAdapter() {
        nowPlayingMovieAdapter = MoviesAdapter(::onItemClicked)
        binding.layNowPlayingMovies.recyclerView.adapter = nowPlayingMovieAdapter
    }

    private fun initializePopularAdapter() {
        popularMovieAdapter = MoviesAdapter(::onItemClicked)
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

    override fun onDestroy() {
        super.onDestroy()
        mainHandler.removeCallbacks(autoScroll)
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(autoScroll)
    }

    private fun onItemClicked(movieId: String) {
//        getMovieDetails(movieId)
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId))
    }
}