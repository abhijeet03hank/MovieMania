package com.hank.moviemania.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface CustomRetrofitCallback<T>: Callback<T> {
    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        response?.takeIf { it -> it.isSuccessful }
    }
}