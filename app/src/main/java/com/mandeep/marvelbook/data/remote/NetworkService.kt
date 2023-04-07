package com.mandeep.marvelbook.data.remote

import com.mandeep.marvelbook.data.model.BaseResponse
import com.mandeep.marvelbook.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.data.remote
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Wed 05 Apr, 2023
 *
 **/

interface NetworkService {

    @GET(ApiRoute.GET_POPULAR)
    suspend fun getPopularMovies(@Query("page") key: Int): Response<BaseResponse<List<Movie>>>

    @GET(ApiRoute.GET_NOW_PLAYING)
    suspend fun getNowPlayingMovies(@Query("page") key: Int): Response<BaseResponse<List<Movie>>>

    @GET(ApiRoute.GET_LATEST)
    suspend fun getLatestMovies(@Query("page") key: Int): Response<BaseResponse<Movie>>

    @GET(ApiRoute.GET_TOP_RATED)
    suspend fun getTopRatedMovies(@Query("page") key: Int): Response<BaseResponse<List<Movie>>>

    @GET(ApiRoute.GET_UPCOMING)
    suspend fun getUpcomingMovies(@Query("page") key: Int): Response<BaseResponse<List<Movie>>>

}