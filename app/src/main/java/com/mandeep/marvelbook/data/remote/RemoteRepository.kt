package com.mandeep.marvelbook.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mandeep.marvelbook.data.base.ApiResponse
import com.mandeep.marvelbook.data.base.NetworkRequest
import com.mandeep.marvelbook.data.base.Resource
import com.mandeep.marvelbook.data.model.Movie
import com.mandeep.marvelbook.data.pagging.NowPlayingPagingSource
import com.mandeep.marvelbook.data.pagging.PopularMoviePagingSource
import com.mandeep.marvelbook.data.pagging.TopRatedPagingSource
import com.mandeep.marvelbook.data.pagging.UpcomingMoviePagingSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.data.remote
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Wed 05 Apr, 2023
 *
 **/
class RemoteRepository @Inject constructor(val networkService: NetworkService) {

    private val config = PagingConfig(20, 4, true, 20)

    fun getPopularMovies(): kotlinx.coroutines.flow.Flow<PagingData<Movie>> {
        return Pager(config) {
            PopularMoviePagingSource(networkService)
        }.flow
    }

    fun getNowPlayingMovies(): kotlinx.coroutines.flow.Flow<PagingData<Movie>> {
        return Pager(config) {
            NowPlayingPagingSource(networkService)
        }.flow
    }

    fun getTopRatedMovies(): kotlinx.coroutines.flow.Flow<PagingData<Movie>> {
        return Pager(config) {
            TopRatedPagingSource(networkService)
        }.flow
    }

    fun getUpComingMovies(): kotlinx.coroutines.flow.Flow<PagingData<Movie>> {
        return Pager(config) {
            UpcomingMoviePagingSource(networkService)
        }.flow
    }

    fun getLatestMovies(): kotlinx.coroutines.flow.Flow<PagingData<Movie>> {
        val config = PagingConfig(20, 4, true, 20)
        return Pager(config) {
            UpcomingMoviePagingSource(networkService)
        }.flow
    }

    fun getMovieDetails(movieId: String) = flow<Resource<Movie>> {
        emit(Resource.Loading())
        try{
            val data = NetworkRequest.process { networkService.getMovieDetail(movieId) }.run {
                when (this) {
                    is ApiResponse.Success -> {
                        results ?: throw Exception("Error in fetching Movie Details.")
                    }
                    is ApiResponse.Failure -> {
                        throw Exception("Error in fetching Movie Details.")
                    }
                }
            }
            emit(Resource.Succes(data))
        }catch (e: Exception){
            emit(Resource.Error(error = e.printStackTrace().toString()))
        }
    }
}