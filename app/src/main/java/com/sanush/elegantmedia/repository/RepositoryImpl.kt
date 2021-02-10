package com.sanush.elegantmedia.repository

import com.sanush.elegantmedia.domain.model.Cat
import com.sanush.elegantmedia.network.RetrofitService

class RepositoryImpl(
    private val retrofitService: RetrofitService
) : Repository {
    override suspend fun get(): List<Cat> {
        return retrofitService.get().data
    }
}