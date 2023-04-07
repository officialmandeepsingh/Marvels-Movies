package com.mandeep.marvelbook.data.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mandeep.marvelbook.data.base.ApiResponse
import com.mandeep.marvelbook.data.base.NetworkRequest
import com.mandeep.marvelbook.data.model.Movie
import com.mandeep.marvelbook.data.remote.NetworkService

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.data.pagging
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Fri 07 Apr, 2023
 *
 **/
class NowPlayingPagingSource(private val service: NetworkService): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val key = params.key ?: 1
        return try {
            val data = NetworkRequest.process { service.getNowPlayingMovies(key) }.run {
                when (this) {
                    is ApiResponse.Success -> {
                        results ?: throw Exception("Error while fetching Now Playing Movies List")
                    }
                    is ApiResponse.Failure -> {
                        throw Exception("Error while fetching Now Playing Movies List")
                    }
                }
            }
            val movies = data.results ?: emptyList()
            val nextPage = data.page?.plus(1)
            LoadResult.Page(movies, null, nextPage)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}