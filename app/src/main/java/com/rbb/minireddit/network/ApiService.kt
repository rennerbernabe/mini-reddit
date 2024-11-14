package com.rbb.minireddit.network

import com.rbb.minireddit.network.model.ListingsResponse
import retrofit2.http.GET

interface ApiService {

    @GET("hot.json")
    suspend fun getHotPosts(): ListingsResponse
}