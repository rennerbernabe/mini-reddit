package com.rbb.minireddit.data

import com.rbb.minireddit.network.ApiService
import com.rbb.minireddit.network.model.ListingsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HomeRepository {
    fun getHotPosts(): Flow<Result<ListingsResponse>>
}

class HomeRepositoryImpl @Inject constructor(private val apiService: ApiService) : HomeRepository {

    override fun getHotPosts(): Flow<Result<ListingsResponse>> = flow {
        val response = apiService.getHotPosts()
        emit(Result.success(response))
    }.catch { exception ->
        emit(Result.failure(exception))
    }
}