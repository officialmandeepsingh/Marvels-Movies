package com.mandeep.marvelbook.data.base

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.data.base
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Wed 05 Apr, 2023
 *
 **/
sealed class ApiResponse<out T> {
    data class Success<out T>(
        val results: T?
    ) : ApiResponse<T>()

    data class Failure<out T>(
        val status_code: Int, val status_message: String, val results: T?,
        val throwable: Throwable? = null
    ) : ApiResponse<T>()

}