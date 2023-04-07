package com.mandeep.marvelbook.data.base

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.data.base
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Wed 05 Apr, 2023
 *
 **/
sealed class Resource<T>(
    val data: T? = null,
    val status: Status = Status.LOADING,
    val message: String? = null,
    val error: String? = null,
) {
    class Succes<T>(data: T? = null, status: Status = Status.SUCCESS, message: String? = null) :
        Resource<T>(data, status, message)

    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(status: Status = Status.ERROR, error: String? = null) :
        Resource<T>(status = status, error = error)

    class UnAuthorized<T>(
        data: T? = null,
        status: Status = Status.UNAUTHORIZED,
        message: String? = null,
    ): Resource<T>(data = data, status = status, message = message)
}
