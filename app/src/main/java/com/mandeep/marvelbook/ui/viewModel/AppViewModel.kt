package com.mandeep.marvelbook.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mandeep.marvelbook.data.base.Resource
import com.mandeep.marvelbook.data.model.BaseResponse
import com.mandeep.marvelbook.data.model.Movie
import com.mandeep.marvelbook.data.remote.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.ui.viewModel
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Wed 05 Apr, 2023
 *
 **/

@HiltViewModel
class AppViewModel @Inject constructor(private val remoteRepository: RemoteRepository) : ViewModel() {

    fun getPopularMovies(): Flow<PagingData<Movie>> {
        return remoteRepository.getPopularMovies().cachedIn(viewModelScope)
    }

    fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return remoteRepository.getNowPlayingMovies().cachedIn(viewModelScope)
    }

    fun getLatestMovies(): Flow<PagingData<Movie>> {
        return remoteRepository.getLatestMovies().cachedIn(viewModelScope)
    }

    fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return remoteRepository.getTopRatedMovies().cachedIn(viewModelScope)
    }

    fun getUpComingMovies(): Flow<PagingData<Movie>> {
        return remoteRepository.getUpComingMovies().cachedIn(viewModelScope)
    }

    fun getMovieDetails(movieId: String): Flow<Resource<Movie>>{
      return remoteRepository.getMovieDetails(movieId)
    }

}