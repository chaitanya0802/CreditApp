package com.chaitanya.creditapp.network

import com.chaitanya.creditapp.credit.ApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("p6764/test_mint/")
    suspend fun fetchData() : ApiResponse
}