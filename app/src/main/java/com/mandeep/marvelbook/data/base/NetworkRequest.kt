package com.mandeep.marvelbook.data.base

import org.json.JSONObject
import retrofit2.Response

/**
 * App Name: Marvel Book
 * Package Name: com.mandeep.marvelbook.data.base
 * Author : Mandeep Singh
 * Email Id: officialmandeepsp@gmail.com
 * Date: Wed 05 Apr, 2023
 *
 **/
object NetworkRequest {
    suspend fun <T> process(api: suspend () -> Response<T>): ApiResponse<T> {
        try {
            val response = api()
            val code = response.code()
            val body = response.body()
            return if (response.isSuccessful) {
                ApiResponse.Success(body)
            } else {
                val rawRes = response.errorBody()?.string() ?: "{}"
                val json = JSONObject(rawRes)
                val m = json.optString("message")
                ApiResponse.Failure(code, m, body)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse.Failure(
                status_code = -1, status_message = e.message ?: "Something went wrong!",
                results = null, throwable = e
            )
        }
    }
}